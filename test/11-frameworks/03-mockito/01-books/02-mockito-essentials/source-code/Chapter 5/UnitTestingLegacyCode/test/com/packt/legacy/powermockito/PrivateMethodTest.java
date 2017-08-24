package com.packt.legacy.powermockito;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PrivateMethod.class)
public class PrivateMethodTest {

	@Test
	public void stubs_private_methods() throws Exception {
		PrivateMethod privateMethodClass = spy(new PrivateMethod());
		when(privateMethodClass, "secretValue").thenReturn("123");
		
		assertEquals("123", privateMethodClass.exposeTheSecretValue());
	}
}
