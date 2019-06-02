package com.loki.funprojects.fundTopHoldings.exception;

public class HtmlParserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public HtmlParserException(String message, Throwable exception) {
		super(message, exception);
	}
	
	public HtmlParserException(String message) {
		super(message);
	}

}
