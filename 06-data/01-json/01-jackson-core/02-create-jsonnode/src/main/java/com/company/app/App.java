package com.company.app;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class App
{
    public static void main( String[] args )
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode whisky = mapper.createObjectNode();
        ((ObjectNode) whisky).put("name", "Nikka");
        ((ObjectNode) whisky).put("origin", "Japanese");

        System.out.println(whisky.toString());
    }
}
/*
output:
{"name":"Nikka","origin":"Japanese"}
 */
