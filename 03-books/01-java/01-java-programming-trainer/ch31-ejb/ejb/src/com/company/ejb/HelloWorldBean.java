package com.company.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/*
 * Session Bean implementation class HelloWorldBean
 */
@Stateless
@LocalBean
public class HelloWorldBean {
	public HelloWorldBean(){		
	}
	public String sayHello(){
		return "Hello from ejb stateless!";
	}
}
