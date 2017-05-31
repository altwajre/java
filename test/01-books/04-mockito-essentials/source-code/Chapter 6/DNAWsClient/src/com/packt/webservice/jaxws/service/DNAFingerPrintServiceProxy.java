package com.packt.webservice.jaxws.service;

public class DNAFingerPrintServiceProxy implements com.packt.webservice.jaxws.service.DNAFingerPrintService {
  private String _endpoint = null;
  private com.packt.webservice.jaxws.service.DNAFingerPrintService dNAFingerPrintService = null;
  
  public DNAFingerPrintServiceProxy() {
    _initDNAFingerPrintServiceProxy();
  }
  
  public DNAFingerPrintServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initDNAFingerPrintServiceProxy();
  }
  
  private void _initDNAFingerPrintServiceProxy() {
    try {
      dNAFingerPrintService = (new com.packt.webservice.jaxws.service.DNAFingerPrintServiceServiceLocator()).getDNAFingerPrintService();
      if (dNAFingerPrintService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)dNAFingerPrintService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)dNAFingerPrintService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (dNAFingerPrintService != null)
      ((javax.xml.rpc.Stub)dNAFingerPrintService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.packt.webservice.jaxws.service.DNAFingerPrintService getDNAFingerPrintService() {
    if (dNAFingerPrintService == null)
      _initDNAFingerPrintServiceProxy();
    return dNAFingerPrintService;
  }
  
  public java.lang.String findMatch(com.packt.webservice.jaxws.dto.DNAProfile dnaProfile) throws java.rmi.RemoteException{
    if (dNAFingerPrintService == null)
      _initDNAFingerPrintServiceProxy();
    return dNAFingerPrintService.findMatch(dnaProfile);
  }
  
  
}