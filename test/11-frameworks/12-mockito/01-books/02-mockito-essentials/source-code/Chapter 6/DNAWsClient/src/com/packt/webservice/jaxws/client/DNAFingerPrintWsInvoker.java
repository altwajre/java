package com.packt.webservice.jaxws.client;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.packt.webservice.jaxws.dto.DNAProfile;
import com.packt.webservice.jaxws.service.DNAFingerPrintServiceServiceLocator;

public class DNAFingerPrintWsInvoker {

	public String findMatch(DNAProfile dnaProfile) throws RemoteException,
			ServiceException {
		return getServiceLocator().getDNAFingerPrintService().findMatch(dnaProfile);
	}

	protected DNAFingerPrintServiceServiceLocator getServiceLocator() {
		return new DNAFingerPrintServiceServiceLocator();
	}

}
