package com.company.app;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class App
{
    public static void main( String[] args ) throws IOException {

        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("curl/NewShopper.curl");
        String curlRequest = IOUtils.toString(inputStream, "UTF-8");

        JSONObject request = new JSONObject();
        JSONObject headers = new JSONObject();

        String httpVerb = getHttpVerb(curlRequest);
        request.put("httpVerb", httpVerb);

        Stream<String> headersStream = getHeaders(curlRequest);

        headersStream.forEach(s ->{
            String[] parts = s.split(": ");
            String key = parts[0];
            int beginIndex = key.indexOf("\"") + 1;
            key = key.substring(beginIndex);
//            System.out.println(key);
            String value = parts[1];
            value = value.substring(0, value.indexOf("\" "));
//            System.out.println(value);
            headers.put(key, value);
        });

        request.put("headers", headers);

        String body = getBody(curlRequest);
        request.put("body", body);
//        System.out.println(body);

        String url = getUrl(curlRequest);
        request.put("url", url);

        System.out.println(request);

    }

    private static String getUrl(String request){
        String[] parts = request.split(" \"http");
        String part = parts[1];
        String url = "http" + part.substring(0, part.length() - 1);
        return url;
    }

    private static String getBody(String request){
        String[] parts = request.split("-D |-d ");
        String part = parts[1];

        String[] strings = part.split(" \"http");
        String body = strings[0];

        return body.substring(1, body.length() - 1);
    }

    private static Stream<String> getHeaders(String request) {
        String[] parts = request.split("-H |-h ");
        List<String> list = Arrays.asList(parts);
        return list.stream().skip(1);
    }

    private static String getHttpVerb(String request) {
        String[] parts = request.split("-X |-x ");
//        System.out.println(parts);
        String part = parts[1];
        return part.substring(0, part.indexOf(" "));
    }
}
