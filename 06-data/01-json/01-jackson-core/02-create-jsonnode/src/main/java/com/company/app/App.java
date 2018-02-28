package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;

public class App
{
    static ObjectMapper mapper = new ObjectMapper();
    public static void main( String[] args ) throws IOException {
        createFromObjectNode();

        createFromString();

    }

    private static void createFromString() throws IOException {
        System.out.println("#createFromString");
        String jsonString = "{\"name\":\"Nikka\",\"origin\":\"Japan\"}";
        JsonNode jsonNode = mapper.readTree(jsonString);
        System.out.println(jsonNode);
    }

    private static void createFromObjectNode() {
        System.out.println("#createFromObjectNode");
        JsonNode whisky = mapper.createObjectNode();
        ((ObjectNode) whisky).put("name", "Nikka");
        ((ObjectNode) whisky).put("origin", "Japanese");

        System.out.println(whisky.toString());
    }
}
/*
output:
#createFromObjectNode
{"name":"Nikka","origin":"Japanese"}
#createFromString
{"name":"Nikka","origin":"Japan"}
 */
