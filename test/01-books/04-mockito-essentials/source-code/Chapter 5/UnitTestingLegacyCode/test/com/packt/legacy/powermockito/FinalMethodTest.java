package com.packt.legacy.powermockito;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FinalMethod.class)
public class FinalMethodTest {

	private static final String A_STUBBED_VALUE = "A stubbed value";

	@Test
	public void stubs_final_methods() throws Exception {
		FinalMethod finalMethod = mock(FinalMethod.class);
		when(finalMethod.getValue()).thenReturn(A_STUBBED_VALUE);
		assertEquals(A_STUBBED_VALUE, finalMethod.getValue());
	}
}
