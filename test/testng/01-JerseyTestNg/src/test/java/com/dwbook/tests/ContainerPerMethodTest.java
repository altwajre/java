package com.dwbook.tests;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import javax.inject.Singleton;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTestNg;

import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

public class ContainerPerMethodTest extends JerseyTestNg.ContainerPerMethodTest {

    @Path("/")
    @Singleton
    @Produces("text/plain")
    public static class Resource {

        private int i = 1;

        @GET
        public int get() {
            return i++;
        }
    }

    @Override
    protected Application configure() {
        return new ResourceConfig(Resource.class);
    }

    @Test(priority = 1)
    public void first() throws Exception {
        test(1);
    }

    @Test(priority = 2)
    public void second() throws Exception {
        test(1);
    }

    @Test(priority = 3)
    public void third() throws Exception {
        test(1);
    }

    private void test(final Integer expected) {
        final Response response = target().request().get();

        assertEquals(response.getStatus(), 200);
        assertEquals(response.readEntity(Integer.class), expected);
    }
}
