package com.loki.funprojects.fundTopHoldings.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableBody;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.loki.funprojects.fundTopHoldings.bean.Fund;
import com.loki.funprojects.fundTopHoldings.bean.TopHolding;
import com.loki.funprojects.fundTopHoldings.exception.HtmlParserException;


@Service
public class YahooSnoopService implements SnoopService{
	Logger logger = LoggerFactory.getLogger(YahooSnoopService.class);
	
	@Autowired
	WebClient webClient;

	public Fund getTop10Holdings(String fundTicker) {
		HtmlPage page = null;
		Fund fundDetails  = null;
		try {  
		  String fundHoldingsUrl = "https://finance.yahoo.com/quote/"+fundTicker+"/holdings?p=" + URLEncoder.encode(fundTicker, "UTF-8");
		  page = webClient.getPage(fundHoldingsUrl);
		  fundDetails = new Fund();
		  List<TopHolding> topHoldings = parseTickerInfoFromYahoo(fundTicker, page);
		  fundDetails.setTopHoldings(topHoldings);
		  fundDetails.setFundTicker(fundTicker);
		}catch(Exception e){
			logger.error("Exception occurred while scrapping fund top holdings from yahoo site, root cause - ", e);
			throw new HtmlParserException("Exception occurred while parsing top holdings information of ticker=["+fundTicker+"]  from yahoo. Root cause - ",e);
		}finally {
			webClient.close();
		}
		
		return fundDetails;
		
		}
	
	private List<TopHolding> parseTickerInfoFromYahoo(String fundTicker, HtmlPage page) {
		
		HtmlElement body = page.getBody();
		List<HtmlElement> elements = body.getElementsByAttribute("div", "data-test", "top-holdings");
		if(elements.size() == 0) {
			throw new HtmlParserException("Provided fund ticker=["+fundTicker+"] is not a valid one.");
		}
		
		HtmlElement element1 = elements.get(0);
		if(!(element1.getChildElementCount() >= 2)) {
			throw new HtmlParserException(" Element is not having top-10 holdings table ");
		}
		
		DomNode childNode = element1.getChildNodes().get(1);
		
		HtmlTable  htmlTableNode = (HtmlTable)childNode;
		
		HtmlTableBody tableBody = htmlTableNode.getBodies().get(0);
		
		DomNodeList<DomNode> tableBodyNodes = tableBody.getChildNodes();
		
		Iterator<DomNode> iterator = tableBodyNodes.iterator();
		List<TopHolding> topHoldings = new ArrayList<>();
		while (iterator.hasNext()) {
			HtmlTableRow tableRowNode = (HtmlTableRow)iterator.next();
			TopHolding topHolding = parseTop10HoldingTableRow(tableRowNode);
			topHoldings.add(topHolding);
		}
		
		return topHoldings;		
		
	}
	
	public TopHolding parseTop10HoldingTableRow(HtmlTableRow tableRowNode) {
		DomNodeList<DomNode> rowCells = tableRowNode.getChildNodes();
		if(!(rowCells.size() >= 3)) {
			throw new HtmlParserException("Top 10 holdings row is not having greater than 3 cells. There must be some change in the format of yahoo top-10 holdinds table structure. Revisit this parsing logic.");
		}
		
		DomNode tickerCell = rowCells.get(1);
		DomNode percentageOfHolding = rowCells.get(2);
		
		String ticker = tickerCell.getTextContent();
		String percentageHoldingStr = percentageOfHolding.getTextContent();
		
		TopHolding holding = new TopHolding();
		holding.setTicker(ticker);		
		holding.setPercentageOfAssets(Double.valueOf(percentageHoldingStr.substring(0, (percentageHoldingStr.length()-1))));
		
		return holding;
		
	}
		
		
		
	}