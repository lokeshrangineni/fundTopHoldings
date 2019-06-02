package com.loki.funprojects.fundTopHoldings.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loki.funprojects.fundTopHoldings.bean.Fund;
import com.loki.funprojects.fundTopHoldings.service.YahooSnoopService;

@RestController
public class FundRestService {
	
	@Autowired
	YahooSnoopService snoopService;
	
	@GetMapping("/fundTopHoldings")
	public Fund getFundTop10Holdings(@RequestParam(name="ticker", required = true) String fundTicker) {
		Fund fundDetails= snoopService.getTop10Holdings(fundTicker);
		return fundDetails;
		
	}

}
