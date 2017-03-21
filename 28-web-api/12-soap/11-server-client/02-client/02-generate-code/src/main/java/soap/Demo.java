
package soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Demo", targetNamespace = "http://soap/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Demo {


    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sum", targetNamespace = "http://soap/", className = "soap.Sum")
    @ResponseWrapper(localName = "sumResponse", targetNamespace = "http://soap/", className = "soap.SumResponse")
    @Action(input = "http://soap/Demo/sumRequest", output = "http://soap/Demo/sumResponse")
    public int sum(
            @WebParam(name = "arg0", targetNamespace = "")
                    int arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    int arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getText", targetNamespace = "http://soap/", className = "soap.GetText")
    @ResponseWrapper(localName = "getTextResponse", targetNamespace = "http://soap/", className = "soap.GetTextResponse")
    @Action(input = "http://soap/Demo/getTextRequest", output = "http://soap/Demo/getTextResponse")
    public String getText(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

}