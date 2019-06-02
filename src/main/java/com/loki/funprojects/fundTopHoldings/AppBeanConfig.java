package com.loki.funprojects.fundTopHoldings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gargoylesoftware.htmlunit.WebClient;

@Configuration
public class AppBeanConfig {
	
	@Bean
	public WebClient getWebClient() {
		WebClient client = new WebClient();  
		client.getOptions().setCssEnabled(false);  
		client.getOptions().setJavaScriptEnabled(false);  
		return client;
	}

}
