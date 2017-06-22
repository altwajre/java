package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.util.List;
import java.util.Map;

class LoadFromResources {

    /*
    yaml:
    - Tom
    - Dick
    - Harry
     */
    public static void loadList() {

        System.out.println("LoadFromResources.loadList()");

        Yaml yaml = new Yaml();
        List<String> list = (List<String>)yaml.load(Util.getLocalResource("spec/list.yaml"));
        System.out.println(list);
    }
    /*
    output:
    LoadFromResources.loadList()
    [Tom, Dick, Harry]
     */


    /*
    yaml:
    123: order1
     */
    public static void loadMap() {

        System.out.println("LoadFromResources.loadMap()");

        Yaml yaml = new Yaml();
        Map map = (Map)yaml.load(Util.getLocalResource("spec/map.yaml"));
        System.out.println(map);
        System.out.println(map.get(123));

    }
    /*
    output:
    LoadFromResources.loadMap()
    {123=order1}
    order1
     */
}

class LoadFromString {

    public static void loadList() {

        System.out.println("LoadFromString.loadList()");
        Yaml yaml = new Yaml();
        String document = "\n- Tom\n- Dick\n- Harry";
        List<String> list = (List<String>) yaml.load(document);
        System.out.println(list);
    }
    /*
    output:
    LoadFromString.loadList()
    [Tom, Dick, Harry]
     */

    public static void loadMap() {

        System.out.println("LoadFromString.loadMap()");

        Yaml yaml = new Yaml();
        String document = "123: order1";
        Map map = (Map) yaml.load(document);
        System.out.println(map);
        System.out.println(map.get(123));

    }
    /*
    output:
    LoadFromString.loadMap()
    {123=order1}
    order1
     */
}

public class App
{
    public static void main( String[] args )
    {
        LoadFromString.loadList();
        LoadFromString.loadMap();

        LoadFromResources.loadList();
        LoadFromResources.loadMap();
    }

}
