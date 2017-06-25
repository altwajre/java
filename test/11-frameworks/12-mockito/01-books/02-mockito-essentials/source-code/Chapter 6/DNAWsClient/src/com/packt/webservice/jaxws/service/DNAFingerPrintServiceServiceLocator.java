/**
 * DNAFingerPrintServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.packt.webservice.jaxws.service;

public class DNAFingerPrintServiceServiceLocator extends org.apache.axis.client.Service implements com.packt.webservice.jaxws.service.DNAFingerPrintServiceService {

    public DNAFingerPrintServiceServiceLocator() {
    }


    public DNAFingerPrintServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public DNAFingerPrintServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for DNAFingerPrintService
    private java.lang.String DNAFingerPrintService_address = "http://localhost:8080/DNACheckWS/services/DNAFingerPrintService";

    public java.lang.String getDNAFingerPrintServiceAddress() {
        return DNAFingerPrintService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String DNAFingerPrintServiceWSDDServiceName = "DNAFingerPrintService";

    public java.lang.String getDNAFingerPrintServiceWSDDServiceName() {
        return DNAFingerPrintServiceWSDDServiceName;
    }

    public void setDNAFingerPrintServiceWSDDServiceName(java.lang.String name) {
        DNAFingerPrintServiceWSDDServiceName = name;
    }

    public com.packt.webservice.jaxws.service.DNAFingerPrintService getDNAFingerPrintService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(DNAFingerPrintService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getDNAFingerPrintService(endpoint);
    }

    public com.packt.webservice.jaxws.service.DNAFingerPrintService getDNAFingerPrintService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.packt.webservice.jaxws.service.DNAFingerPrintServiceSoapBindingStub _stub = new com.packt.webservice.jaxws.service.DNAFingerPrintServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getDNAFingerPrintServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setDNAFingerPrintServiceEndpointAddress(java.lang.String address) {
        DNAFingerPrintService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.packt.webservice.jaxws.service.DNAFingerPrintService.class.isAssignableFrom(serviceEndpointInterface)) {
                com.packt.webservice.jaxws.service.DNAFingerPrintServiceSoapBindingStub _stub = new com.packt.webservice.jaxws.service.DNAFingerPrintServiceSoapBindingStub(new java.net.URL(DNAFingerPrintService_address), this);
                _stub.setPortName(getDNAFingerPrintServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("DNAFingerPrintService".equals(inputPortName)) {
            return getDNAFingerPrintService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.jaxws.webservice.packt.com", "DNAFingerPrintServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.jaxws.webservice.packt.com", "DNAFingerPrintService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("DNAFingerPrintService".equals(portName)) {
            setDNAFingerPrintServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
