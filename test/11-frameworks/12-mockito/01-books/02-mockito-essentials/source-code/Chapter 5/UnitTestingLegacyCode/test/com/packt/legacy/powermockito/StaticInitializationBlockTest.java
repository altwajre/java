package com.packt.legacy.powermockito;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor("com.packt.legacy.powermockito.StaticInitializationBlock")
public class StaticInitializationBlockTest {

	@Test
	public void supresses_static_initialization_blocks() throws Exception {
		assertEquals(0,StaticInitializationBlock.value);
	}
}
