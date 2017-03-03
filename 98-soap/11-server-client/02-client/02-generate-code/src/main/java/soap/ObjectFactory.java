
package soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Sum_QNAME = new QName("http://soap/", "sum");
    private final static QName _GetTextResponse_QNAME = new QName("http://soap/", "getTextResponse");
    private final static QName _SumResponse_QNAME = new QName("http://soap/", "sumResponse");
    private final static QName _GetText_QNAME = new QName("http://soap/", "getText");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetText }
     * 
     */
    public GetText createGetText() {
        return new GetText();
    }

    /**
     * Create an instance of {@link GetTextResponse }
     * 
     */
    public GetTextResponse createGetTextResponse() {
        return new GetTextResponse();
    }

    /**
     * Create an instance of {@link Sum }
     * 
     */
    public Sum createSum() {
        return new Sum();
    }

    /**
     * Create an instance of {@link SumResponse }
     * 
     */
    public SumResponse createSumResponse() {
        return new SumResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Sum }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "sum")
    public JAXBElement<Sum> createSum(Sum value) {
        return new JAXBElement<Sum>(_Sum_QNAME, Sum.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getTextResponse")
    public JAXBElement<GetTextResponse> createGetTextResponse(GetTextResponse value) {
        return new JAXBElement<GetTextResponse>(_GetTextResponse_QNAME, GetTextResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SumResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "sumResponse")
    public JAXBElement<SumResponse> createSumResponse(SumResponse value) {
        return new JAXBElement<SumResponse>(_SumResponse_QNAME, SumResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetText }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "getText")
    public JAXBElement<GetText> createGetText(GetText value) {
        return new JAXBElement<GetText>(_GetText_QNAME, GetText.class, null, value);
    }

}
