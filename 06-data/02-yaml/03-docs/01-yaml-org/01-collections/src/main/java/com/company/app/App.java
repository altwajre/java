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
    public static void main( String[] args ) throws Exception {

        sequenceOfScalars();

        mappingScalarsToScalars();

        mappingScalarsToSequences();

        sequenceOfMappings();

        sequenceOfSequences();

        mappingOfMappings();

    }

    /*
    yaml:
    #Example 2.6. Mapping of Mappings
    Mark McGwire: {hr: 65, avg: 0.278}
    Sammy Sosa: {
      hr: 63,
      avg: 0.288
    }
     */
    private static void mappingOfMappings() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/06-mapping-of-mappings.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, Map<String, Double>> map = (Map<String, Map<String, Double>>)yaml.load(inputStream);
        map.entrySet().forEach(parentMap -> {
            System.out.println(parentMap.getKey() + ":");
            parentMap.getValue().entrySet().forEach(childMap -> {
                System.out.println("  " + childMap.getKey() + ": " + childMap.getValue());
            });
        });
    }
    /*
    output:
    Mark McGwire:
      hr: 65
      avg: 0.278
    Sammy Sosa:
      hr: 63
      avg: 0.288
     */

    /*
    yaml:
    #Example 2.5. Sequence of Sequences
    - [name        , hr, avg  ]
    - [Mark McGwire, 65, 0.278]
    - [Sammy Sosa  , 63, 0.288]
     */
    private static void sequenceOfSequences() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/05-sequence-of-sequences.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        List<List<Object>> list = (List<List<Object>>)yaml.load(inputStream);

        list.forEach(l -> {
            System.out.print("- ");
            l.forEach(i -> System.out.print(i + " "));
            System.out.println("");
        });
    }
    /*
    output:
    - name hr avg
    - Mark McGwire 65 0.278
    - Sammy Sosa 63 0.288
     */

    /*
    yaml:
    #Example 2.4. Sequence of Mappings
    #(players’ statistics)
    -
      name: Mark McGwire
      hr:   65
      avg:  0.278
    -
      name: Sammy Sosa
      hr:   63
      avg:  0.288
     */
    private static void sequenceOfMappings() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/04-sequence-of-mappings.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        List<Map<String, Object>> list = (List<Map<String, Object>>)yaml.load(inputStream);

        list.forEach(map -> {
            System.out.println("-");
            for (String key : map.keySet()){
                System.out.println("  " + key + ": " + map.get(key));
            }
        });

        // Or below
//        list.forEach(map -> {
//            System.out.println("-");
//            map.entrySet().forEach(e -> {
//                System.out.println("  " + e.getKey() + ": " + e.getValue());
//            });
//        });

    }
    /*
    output:
    -
      name: Mark McGwire
      hr: 65
      avg: 0.278
    -
      name: Sammy Sosa
      hr: 63
      avg: 0.288
     */

    /*
    yaml:
    #Example 2.3. Mapping Scalars to Sequences
    #(ball clubs in each league)
    american:
      - Boston Red Sox
      - Detroit Tigers
      - New York Yankees
    national:
      - New York Mets
      - Chicago Cubs
      - Atlanta Braves
     */
    private static void mappingScalarsToSequences() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/03-mapping-scalars-to-sequences.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, List<String>> map = (Map<String, List<String>>)yaml.load(inputStream);
        System.out.println(map);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    {american=[Boston Red Sox, Detroit Tigers, New York Yankees], national=[New York Mets, Chicago Cubs, Atlanta Braves]}
    american: [Boston Red Sox, Detroit Tigers, New York Yankees]
    national: [New York Mets, Chicago Cubs, Atlanta Braves]
     */

    /*
    yaml:
    #Example 2.2. Mapping Scalars to Scalars
    #(player statistics)
    hr:  65    # Home runs
    avg: 0.278 # Batting average
    rbi: 147   # Runs Batted In
     */
    private static void mappingScalarsToScalars() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/02-mapping-scalars-to-scalars.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, Double> map = (Map<String, Double>)yaml.load(inputStream);
        System.out.println(map);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    {hr=65, avg=0.278, rbi=147}
    hr: 65
    avg: 0.278
    rbi: 147
     */

    /*
    yaml:
    #Example 2.1. Sequence of Scalars
    #(ball players)
    - Mark McGwire
    - Sammy Sosa
    - Ken Griffey
     */
    private static void sequenceOfScalars() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/01-sequence-of-scalars.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        List<String> list = (List<String>)yaml.load(inputStream);
        System.out.println(list);

    }
    /*
    output:
    [Mark McGwire, Sammy Sosa, Ken Griffey]
     */
}
