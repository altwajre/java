package com.packt.legacy.powermockito;

public class SuppressSuperConstructor extends DontExtendMePlease{

	public SuppressSuperConstructor() {
		super();
	}

}

class DontExtendMePlease{
	DontExtendMePlease(){
		int x =1/0;
	}
}
