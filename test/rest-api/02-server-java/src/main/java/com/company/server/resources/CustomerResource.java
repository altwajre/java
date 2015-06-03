package com.company.server.resources;

import com.company.server.core.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.ws.WebServiceException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/customers")
public class CustomerResource {
    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
    private AtomicInteger idCounter = new AtomicInteger();

    @POST
    @Consumes("application/xml")
    public Response createCustomer(InputStream is){
        Customer customer = readCustomer(is);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Create customer " + customer.getId());
        return Response.created(URI.create("/customers/" + customer.getId())).build();
    }

    protected Customer readCustomer(InputStream is){
        try{
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(is);
            Element root = doc.getDocumentElement();
            Customer cust = new Customer();
            if(root.getAttribute("id") != null && !root.getAttribute("id").trim().equals(""))
                cust.setId(Integer.valueOf(root.getAttribute("id")));
            NodeList nodes = root.getChildNodes();
            for(int i = 0; i < nodes.getLength(); i++){
                Element element = (Element) nodes.item(i);
                if(element.getTagName().equals("first-name")){
                    cust.setFirstName(element.getTextContent());
                }
                else if(element.getTagName().equals("last-name")){
                    cust.setLastName(element.getTextContent());
                }
                else if (element.getTagName().equals("street")) {
                    cust.setStreet(element.getTextContent());
                }
                else if (element.getTagName().equals("city")) {
                    cust.setCity(element.getTextContent());
                }
                else if (element.getTagName().equals("state")) {
                    cust.setState(element.getTextContent());
                }
                else if (element.getTagName().equals("zip")) {
                    cust.setZip(element.getTextContent());
                }
                else if (element.getTagName().equals("country")) {
                    cust.setCountry(element.getTextContent());
                }
            }
            return cust;
        } catch (Exception e) {
            throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
        }
    }
}

