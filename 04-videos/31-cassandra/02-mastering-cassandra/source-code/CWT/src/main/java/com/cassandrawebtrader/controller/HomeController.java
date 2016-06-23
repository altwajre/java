package com.cassandrawebtrader.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping
	public String showHomePage() {
		return "home";
	}

}
