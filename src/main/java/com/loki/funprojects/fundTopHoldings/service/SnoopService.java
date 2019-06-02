package com.loki.funprojects.fundTopHoldings.service;

import com.loki.funprojects.fundTopHoldings.bean.Fund;

public interface SnoopService {
	
	public Fund getTop10Holdings(String fundTicker);

}
