package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        twoDocumentsLists();

        twoDocumentsMaps();

        singleDocumentWithTwoComments();

        repeatedNodes();

        complexMapping();

        compactNestedMapping();

    }

    /*
    yaml
    #Example 2.12. Compact Nested Mapping
    ---
    # Products purchased
    - item    : Super Hoop
      quantity: 1
    - item    : Basketball
      quantity: 4
    - item    : Big Shoes
      quantity: 1
     */
    private static void compactNestedMapping() throws FileNotFoundException {
        String pathname = "src/main/resources/spec/06-compact-nested-mapping.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        List<Map<String, Object>> list = (List<Map<String, Object>>)yaml.load(inputStream);

        list.forEach(map -> {
            System.out.print("-");
            for (String key : map.keySet()){
                System.out.println("  " + key + ": " + map.get(key));
            }
        });
    }
    /*
    output:
    -  item: Super Hoop
      quantity: 1
    -  item: Basketball
      quantity: 4
    -  item: Big Shoes
      quantity: 1
     */

    /*
    A question mark and space ("? ") indicate a complex mapping key.
    Within a block collection, ("key: value") pairs can start immediately following the dash, colon, or question mark.

    yaml:
    #Example 2.11. Mapping between Sequences
    ? - Detroit Tigers
      - Chicago cubs
    :
      - 2001-07-23

    ? [ New York Yankees,
        Atlanta Braves ]
    : [ 2001-07-02, 2001-08-12,
        2001-08-14 ]
     */
    private static void complexMapping() throws FileNotFoundException {
        String pathname = "src/main/resources/spec/05-complex-mapping.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<Object, List<Object>> map = (Map<Object, List<Object>>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    [Detroit Tigers, Chicago cubs]: [Sun Jul 22 17:00:00 PDT 2001]
    [New York Yankees, Atlanta Braves]: [Sun Jul 01 17:00:00 PDT 2001, Sat Aug 11 17:00:00 PDT 2001, Mon Aug 13 17:00:00 PDT 2001]
     */

    /*
    Repeated nodes (objects) are first identified by an anchor (marked with the ampersand - "&")
    and are then aliased (referenced with an asterisk - "*")

    yaml:
    #Example 2.10.  Node for “Sammy Sosa”
    #appears twice in this document
    ---
    hr:
      - Mark McGwire
      # Following node labeled SS
      - &SS Sammy Sosa
    rbi:
      - *SS # Subsequent occurrence
      - Ken Griffey
     */
    private static void repeatedNodes() throws FileNotFoundException {
        String pathname = "src/main/resources/spec/04-repeated-nodes.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        for (Object document : yaml.loadAll(inputStream)) {
            System.out.println(document);
            Map<String, Object> map = (Map<String, Object>)document;
            map.entrySet().forEach(e -> {
                System.out.println("  " + e.getKey() + ": " + e.getValue());
            });
        }
    }
    /*
    output:
    {hr=[Mark McGwire, Sammy Sosa], rbi=[Sammy Sosa, Ken Griffey]}
      hr: [Mark McGwire, Sammy Sosa]
      rbi: [Sammy Sosa, Ken Griffey]
     */

    /*
    yaml:
    #Example 2.9.  Single Document with
    #Two Comments
    ---
    hr: # 1998 hr ranking
      - Mark McGwire
      - Sammy Sosa
    rbi:
      # 1998 rbi ranking
      - Sammy Sosa
      - Ken Griffey
     */
    private static void singleDocumentWithTwoComments() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/03-single-document-with-two-comments.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, List<String>> map = (Map<String, List<String>>)yaml.load(inputStream);
        System.out.println(map);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    {hr=[Mark McGwire, Sammy Sosa], rbi=[Sammy Sosa, Ken Griffey]}
    hr: [Mark McGwire, Sammy Sosa]
    rbi: [Sammy Sosa, Ken Griffey]
     */

    /*
    yaml:
    #Example 2.8.  Play by Play Feed
    #from a Game
    ---
    time: 20:03:20
    player: Sammy Sosa
    action: strike (miss)
    ...
    ---
    time: 20:03:47
    player: Sammy Sosa
    action: grand slam
    ...
     */
    private static void twoDocumentsMaps() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/02-two-documents-maps.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        for (Object document : yaml.loadAll(inputStream)) {
            System.out.println(document);
            Map<String, Object> map = (Map<String, Object>)document;
            map.entrySet().forEach(e -> {
                System.out.println("  " + e.getKey() + ": " + e.getValue());
            });
        }
    }
    /*
    output:
    {time=72200, player=Sammy Sosa, action=strike (miss)}
      time: 72200
      player: Sammy Sosa
      action: strike (miss)
    {time=72227, player=Sammy Sosa, action=grand slam}
      time: 72227
      player: Sammy Sosa
      action: grand slam
     */

    /*
    yaml:
    #Example 2.7.  Two Documents in a Stream
    #(each with a leading comment)
    # Ranking of 1998 home runs
    ---
    - Mark McGwire
    - Sammy Sosa
    - Ken Griffey

    # Team ranking
    ---
    - Chicago Cubs
    - St Louis Cardinals
     */
    private static void twoDocumentsLists() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/01-two-documents-lists.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        for (Object document : yaml.loadAll(inputStream)) {
            System.out.println(document);
            List<String> list = (List<String>)document;
            list.forEach(l -> {
                System.out.println("  "+ l);
            });
        }

    }
    /*
    output:
    [Mark McGwire, Sammy Sosa, Ken Griffey]
      Mark McGwire
      Sammy Sosa
      Ken Griffey
    [Chicago Cubs, St Louis Cardinals]
      Chicago Cubs
      St Louis Cardinals
     */

}
