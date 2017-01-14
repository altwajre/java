package com.company.app.resources;

import com.company.app.model.Product;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    private List<Product> existingProducts = new ArrayList<Product>();

    @GET
    public Response getProducts(){
        return Response.ok(existingProducts).build();
    }

    @GET
    @Path("/{id}")
    public Response getProduct(@PathParam("id") int id){
        return Response
                .ok("{product_id: " + Integer.toString(id) + ", name: " + existingProducts.get(id) + "}")
                .build();
    }

    @POST
    public Response createProduct(){
        Product product = new Product();
        product.setName("A product");
        product.setPrice(0);
        product.setStock(0);
        product.setWeight(0);
        product.setId(existingProducts.size());
        existingProducts.add(product);

        return Response.status(Response.Status.CREATED)
                .entity(product)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProduct(@PathParam("id") int id, @FormParam("name") String name){
        Product product = existingProducts.get(id);
        product.setName(name);
        return Response
                .ok("{product_id: " + id + ", name: " + name + "}")
                .build();
    }

    @DELETE
    public Response deleteAllProducts(){
        existingProducts.clear();
        return Response.noContent().build();
    }
}
