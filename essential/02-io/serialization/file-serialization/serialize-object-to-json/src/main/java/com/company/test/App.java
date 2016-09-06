package com.company.test;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException {
        JavaObjectToJson();

        JsonToJavaObject();
    }

    private static void JavaObjectToJson() throws IOException {
        System.out.println("#JavaObjectToJson");

        User user = new User();
        user.setAge(18);
        user.setName("Tom");
        List<String> messages = new ArrayList<String>() {
            {
                add("msg 1");
                add("msg 2");
                add("msg 3");
            }
        };
        user.setMessages(messages);

        ObjectMapper mapper = new ObjectMapper();

        mapper.writeValue(new File("/tmp/user.json"), user);
    }

    private static void JsonToJavaObject() throws IOException {
        System.out.println("\n#JsonToJavaObject");

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new File("/tmp/user.json"), User.class);
        System.out.println(user);
    }
}
