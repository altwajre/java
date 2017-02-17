package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws IOException {
        ClassLoader classLoader = App.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("json//test.json");
        Map mapJson = new ObjectMapper().readValue(inputStream, Map.class);
        System.out.println("# Map Json");
        System.out.println(mapJson);

        System.out.println("## Cast json array to List");
        List items = (List)mapJson.get("items");
        System.out.println(items);

        System.out.println("## Get first item of json array");
        Map item = (Map)items.get(0);
        System.out.println(item);
        System.out.println(item.get("id"));

        System.out.println("# Map to JSONObject");
        JSONObject jsonObject = new JSONObject(mapJson);
        System.out.println(jsonObject.toString());

        System.out.println("# JSONObject to Map");
        Map map = jsonObject;
        System.out.println(map);
        System.out.println(map.get("class"));
    }
}
/*
output:
# Map Json
{class={name=Math, students={123=Tom, 234=Jen}}, items=[{item={name=item1}, id=111}, {item={name=item2}, id=222}]}
## Cast json array to List
[{item={name=item1}, id=111}, {item={name=item2}, id=222}]
## Get first item of json array
{item={name=item1}, id=111}
111
# Map to JSONObject
{"class":{"name":"Math","students":{"123":"Tom","234":"Jen"}},"items":[{"item":{"name":"item1"},"id":111},{"item":{"name":"item2"},"id":222}]}
# JSONObject to Map
{"class":{"name":"Math","students":{"123":"Tom","234":"Jen"}},"items":[{"item":{"name":"item1"},"id":111},{"item":{"name":"item2"},"id":222}]}
{name=Math, students={123=Tom, 234=Jen}}
 */
