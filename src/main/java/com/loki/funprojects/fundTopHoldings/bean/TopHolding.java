package com.loki.funprojects.fundTopHoldings.bean;

import java.io.Serializable;

public class TopHolding implements Serializable{
	private static final long serialVersionUID = -6212277200272395301L;
	
	String ticker;
	Double percentageOfAssets;
	
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public Double getPercentageOfAssets() {
		return percentageOfAssets;
	}
	public void setPercentageOfAssets(Double percentageOfAssets) {
		this.percentageOfAssets = percentageOfAssets;
	}
	
	

}
