package com.company.app;

import org.apache.commons.io.IOUtils;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("data.xml");
        String requestData = IOUtils.toString(inputStream, "UTF-8");

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
/*
output:
InboundJaxrsResponse{context=ClientResponse{method=POST, uri=http://localhost:8080/soap/DemoService?wsdl, status=200, reason=OK}}
<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><ns2:getTextResponse xmlns:ns2="http://soap/"><return>Hello tom from soap</return></ns2:getTextResponse></S:Body></S:Envelope>
 */
