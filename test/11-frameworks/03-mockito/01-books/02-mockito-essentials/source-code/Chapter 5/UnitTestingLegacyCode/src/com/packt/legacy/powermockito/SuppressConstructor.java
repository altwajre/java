package com.packt.legacy.powermockito;

public class SuppressConstructor {

	public int someValue = 100;
	public SuppressConstructor(int val){
		val = val/0;
	}
	
	public SuppressConstructor(){
		someValue = 9/0;
	}
}
