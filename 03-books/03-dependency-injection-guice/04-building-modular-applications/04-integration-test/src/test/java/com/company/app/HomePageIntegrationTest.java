package com.company.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

class HomeServlet extends HttpServlet {}
class WebModule extends AbstractModule{
    @Override
    protected void configure() {
//        bind(HomeServlet.class);
    }
}
class TestPersistenceModule extends AbstractModule{
    @Override
    protected void configure() {
    }
}
public class HomePageIntegrationTest {
    private Injector injector;
    @Before
    public final void prepareContainer(){
        injector = Guice.createInjector(new WebModule(), new TestPersistenceModule());
    }
    @After
    public final void cleanup(){}
    @Test
    public final void renderHomePage() throws Exception{
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        injector.getInstance(HomeServlet.class).service(request, response);
    }
}
