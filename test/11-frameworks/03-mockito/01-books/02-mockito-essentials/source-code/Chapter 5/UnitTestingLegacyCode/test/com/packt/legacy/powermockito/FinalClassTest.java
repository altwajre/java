package com.packt.legacy.powermockito;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemVerifier.class)
public class FinalClassTest {

	@Test
	public void mocks_final_classes() throws Exception {
		SystemVerifier systemVerifier = mock(SystemVerifier.class);
		when(systemVerifier.isInstallable()).thenReturn(true);
		
		SoftwareInstaller installer = new SoftwareInstaller(systemVerifier);
		assertTrue(installer.install("java"));
	}
}
