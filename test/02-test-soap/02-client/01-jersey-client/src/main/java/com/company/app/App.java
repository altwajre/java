package com.company.app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.soap.*;
import java.io.ByteArrayOutputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        MessageFactory mf = MessageFactory.newInstance();
        SOAPMessage request = mf.createMessage();

        SOAPPart part = request.getSOAPPart();

        SOAPEnvelope env = part.getEnvelope();

        env.addNamespaceDeclaration("S", "http://schemas.xmlsoap.org/soap/envelope/");
        env.addNamespaceDeclaration("SOAP-ENV", "http://schemas.xmlsoap.org/soap/envelope/");
        SOAPBody body = env.getBody();
        SOAPElement tag = body.addChildElement("getText");
        tag.addNamespaceDeclaration("ns2", "http://soap/");
        SOAPElement arg0 = tag.addChildElement("arg0");
        arg0.addTextNode("tom");

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        request.writeTo(stream);
        String requestx = new String(stream.toByteArray(), "utf-8");


        String requestData = "";

        Client client = ClientBuilder.newClient();
        Response response = client.target("http://localhost:8080/soap/DemoService?wsdl")
                .request()
                .header("soapAction", "getText")
                .post(Entity.entity(requestData, MediaType.TEXT_XML));
        System.out.println(response);
        response.bufferEntity();
        String responseBody = response.readEntity(String.class);
        System.out.println(responseBody);
    }
}
