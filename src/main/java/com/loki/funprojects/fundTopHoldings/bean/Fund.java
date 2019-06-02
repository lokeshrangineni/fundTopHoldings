package com.loki.funprojects.fundTopHoldings.bean;

import java.io.Serializable;
import java.util.List;

public class Fund implements Serializable{
	
	private static final long serialVersionUID = 5401930176120720733L;
	
	String fundTicker;
	List<TopHolding> topHoldings;
	
	public String getFundTicker() {
		return fundTicker;
	}
	public void setFundTicker(String fundTicker) {
		this.fundTicker = fundTicker;
	}
	public List<TopHolding> getTopHoldings() {
		return topHoldings;
	}
	public void setTopHoldings(List<TopHolding> topHoldings) {
		this.topHoldings = topHoldings;
	}
	
	

}
