package com.company.app;

import org.apache.commons.io.IOUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args )
    {
        load_list_from_memory();

        load_list();

        load_map();

        load_documents();

        load_any_object();

    }

    private static void load_any_object() {
        System.out.println("#load_any_object");
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/any-object.yaml"));
            String document = IOUtils.toString(inputStream, "UTF-8");
            Yaml yaml = new Yaml();
            Map<Integer, String> map = (Map<Integer, String>)yaml.load(document);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
output:
#load_any_object
{none=[null, null], bool=[true, false, true, false], int=42, float=3.14159, list=[Tom, Dick, Harry], map={1=Tom, 2=Dick}}
 */

    private static void load_documents() {
        System.out.println("#load_documents - use Yaml.loadAll() to load list and map");
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/list-map.yaml"));
            Yaml yaml = new Yaml();
            for(Object data : yaml.loadAll(inputStream)){
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
output:
#load_documents - use Yaml.loadAll() to load list and map
[Tom, Dick, Harry]
{1=Tom, 2=Dick, 3=Harry}
 */

    private static void load_map() {
        System.out.println("#load_map");
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/map.yaml"));
            Yaml yaml = new Yaml();
            Map<Integer, String> map = (Map<Integer, String>)yaml.load(inputStream);
            System.out.println(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
output:
#load_map
{1=Tom, 2=Dick, 3=Harry}
 */

    private static void load_list() {
        System.out.println("#load_list");
        try {
            InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/list.yaml"));
            Yaml yaml = new Yaml();
            List<String> list = (List<String>)yaml.load(inputStream);
            System.out.println(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
/*
output:
#load_list
[Tom, Dick, Harry]
 */

    private static void load_list_from_memory() {
        System.out.println("#load_list_from_memory");

        Yaml yaml = new Yaml();

        String document = "\n- Tom\n- Dick\n- Harry";
        List<String> list = (List<String>)yaml.load(document);

        System.out.println(list);
    }
/*
output:
#load_list_from_memory
[Tom, Dick, Harry]
 */
}
