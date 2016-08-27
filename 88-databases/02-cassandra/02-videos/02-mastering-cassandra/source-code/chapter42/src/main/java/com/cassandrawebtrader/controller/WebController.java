package com.cassandrawebtrader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	private static Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@RequestMapping("/")
	public String index() {
		logger.info("/ is called");
		
		return "index";
	}

}
