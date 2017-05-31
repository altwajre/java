package com.packt.webservice.jaxws.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.when;

import javax.xml.rpc.ServiceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.packt.webservice.jaxws.dto.DNAProfile;
import com.packt.webservice.jaxws.service.DNAFingerPrintService;
import com.packt.webservice.jaxws.service.DNAFingerPrintServiceServiceLocator;

@RunWith(MockitoJUnitRunner.class)
public class DNAFingerPrintWsInvokerTest {

	DNAFingerPrintWsInvoker invoker;
	@Mock DNAFingerPrintService mockService;
	@Mock DNAFingerPrintServiceServiceLocator mockLocator;
	
	@Before
	public void setup() throws ServiceException {
		invoker = new DNAFingerPrintWsInvoker(){
			protected DNAFingerPrintServiceServiceLocator getServiceLocator() {
				return mockLocator;
			}
		};

		when(mockLocator.getDNAFingerPrintService()).thenReturn(mockService);
	}
	
    @Test
	public void finds_DNA_match() throws Exception {
		when(mockService.findMatch(isA(DNAProfile.class))).thenReturn("Sherlock");
		assertEquals("Sherlock", invoker.findMatch(new DNAProfile()));
	}
}
