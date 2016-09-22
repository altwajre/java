package com.company.app;

import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

// https://commons.apache.org/proper/commons-lang/javadocs/api-3.1/org/apache/commons/lang3/text/StrSubstitutor.html
public class App
{
    public static void main( String[] args )
    {
        String template = "The ${animal} jumped over the ${target}.";

        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("animal", "quick brown fox");
        valuesMap.put("target", "lazy dog");
        StrSubstitutor substitutor = new StrSubstitutor(valuesMap);
        String result = substitutor.replace(template);
        System.out.println(result);
    }
}
/*
output:
The quick brown fox jumped over the lazy dog.
 */
