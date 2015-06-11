package com.company.ecomm.tests.helpers;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.mortbay.util.ajax.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;

public class Helpers {
    Random generator = new Random();
    Logger logger = LoggerFactory.getLogger(Helpers.class);

    public JSONObject httpToJson(HttpResponse response) throws IOException {
        return (JSONObject) JSON.parse(EntityUtils.toString(response.getEntity()));
    }

    public String getResponseBodyAsString(HttpResponse httpResponse) throws IOException {
        return EntityUtils.toString(httpResponse.getEntity());
    }
}
