package com.company.app;

import io.vertx.core.json.JsonArray;

/*
http://vertx.io/docs/vertx-core/java/#_json_arrays

JSON arrays
The JsonArray class represents JSON arrays.

A JSON array is a sequence of values (string, number, boolean).

JSON arrays can also contain null values.
 */
public class App
{
    public static void main( String[] args )
    {
        createFromString();

        addAndGet();
    }

    private static void addAndGet() {

        System.out.println("#addAndGet");

        JsonArray array = new JsonArray();

        array.add("foo").add(123).add(false);

        System.out.println(array.getString(0));
        System.out.println(array.getInteger(1));
        System.out.println(array.getBoolean(2));
    }

    private static void createFromString() {

        System.out.println("#createFromString");

        String jsonString = "[\"Tom\",\"Dick\"]";
        JsonArray array = new JsonArray(jsonString);
        System.out.println(array);
    }
}
/*
output:
#createFromString
["Tom","Dick"]
#addAndGet
foo
123
false
 */
