package com.packt.legacy.powermockito;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MedicalBill.class)
public class StaticMethodTest {

	@Test
	public void stubs_static_methods() throws Exception {
		System.out.println(MedicalBill.generateId());
		mockStatic(MedicalBill.class);
        when(MedicalBill.generateId()).thenReturn(1);
        assertEquals(1, MedicalBill.generateId());
	}
}
