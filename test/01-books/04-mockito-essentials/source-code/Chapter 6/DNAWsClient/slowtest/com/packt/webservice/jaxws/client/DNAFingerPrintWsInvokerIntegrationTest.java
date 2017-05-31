package com.packt.webservice.jaxws.client;

import static org.junit.Assert.assertEquals;

import javax.xml.rpc.ServiceException;

import org.junit.Before;
import org.junit.Test;

import com.packt.webservice.jaxws.dto.DNAProfile;


public class DNAFingerPrintWsInvokerIntegrationTest {

	DNAFingerPrintWsInvoker invoker;

	@Before
	public void setup() throws ServiceException {
		invoker = new DNAFingerPrintWsInvoker();
	}
	
    @Test
	public void finds_DNA_match() throws Exception {
		assertEquals("sujoy", invoker.findMatch(new DNAProfile()));
	}
}
