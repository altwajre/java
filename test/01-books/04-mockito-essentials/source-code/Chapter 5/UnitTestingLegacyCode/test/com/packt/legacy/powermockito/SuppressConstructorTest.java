package com.packt.legacy.powermockito;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class SuppressConstructorTest {

	@Test
	public void supresses_own_constructor() throws Exception {
		SuppressConstructor nasty = Whitebox.newInstance(SuppressConstructor.class);
		assertNotNull(nasty);
	}
}
