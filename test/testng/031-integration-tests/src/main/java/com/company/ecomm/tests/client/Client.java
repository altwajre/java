package com.company.ecomm.tests.client;

import com.company.ecomm.tests.testbase.Global;
import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class Client {
    Logger logger = LoggerFactory.getLogger(Client.class);

    public String accessKey = null;
    public String secretKey = null;
    public HttpRequstBuilder requstBuilder = null;
    public HttpClient client = null;
    public URI uri;

    public Client() throws Exception{
        this.accessKey = Global.MASTER_CREDS_DEFAULT.accessKey;
    }
}
