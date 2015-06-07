package com.dwbook.phonebook;

import com.dwbook.phonebook.resources.ClientResource;
import com.dwbook.phonebook.resources.ContactResource;
import com.google.common.cache.CacheBuilderSpec;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.CachingAuthenticator;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jdbi.DBIFactory;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import sun.security.krb5.internal.Authenticator;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.server.ResourceConfig;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;

public class App extends Application<PhonebookConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    @Override
    public void initialize(Bootstrap<PhonebookConfiguration> b) {
    }

    @Override
    public void run(PhonebookConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < c.getMessageRepetitions(); i++) {
            System.out.println(c.getMessage());
        }
        System.out.println(c.getAdditionalMessage());

        // Create a DBI factory and build a JDBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(e, c.getDataSourceFactory(), "mysql");
        // Add the resource to the environment
        e.jersey().register(new ContactResource(jdbi));

        // build the client and add the resource to the environment
        CredentialsProvider credentialsProvider = new org.apache.http.impl.client.BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials("will", "pass")
        );

        ClientConfig cc = new ClientConfig();
        cc.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credentialsProvider);
        cc.connectorProvider(new ApacheConnectorProvider());
        Client client = ClientBuilder.newClient(cc);

        e.jersey().register(new ClientResource(client));

        // Authenticator, with caching support (CachingAuthenticator)
        CachingAuthenticator<BasicCredentials, Boolean> authenticator = new CachingAuthenticator<BasicCredentials, Boolean>(
                e.metrics(),
                new PhonebookAuthenticator(jdbi),
                CacheBuilderSpec.parse("maximumSize=10000, expireAfterAccess=10m"));

        // Register the authenticator with the environment
        e.jersey().register(AuthFactory.binder(
                new BasicAuthFactory<Boolean>(
                        new PhonebookAuthenticator(jdbi), "SUPER SECRET STUFF", Boolean.class)));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
