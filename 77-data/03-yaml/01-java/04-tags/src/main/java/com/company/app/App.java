package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class App
{
    public static void main( String[] args ) throws Exception
    {

        integers();

        floatingPoint();

        miscellaneous();

        timestamps();

        ExplicitTagsTypesTest.variousExplicitTags();

        GlobalTagsPojosTest.globalTags();

        unorderedSets();

        orderedMappings();

    }

    /*
    yaml:
    # Ordered maps are represented as
    # A sequence of mappings, with
    # each mapping having one key
    --- !!omap
    - Mark McGwire: 65
    - Sammy Sosa: 63
    - Ken Griffy: 58
     */
    private static void orderedMappings() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/08-ordered-mappings.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, Integer> map = (Map<String, Integer>)yaml.load(inputStream);
        System.out.println(map.getClass().getSimpleName());
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    LinkedHashMap
    Mark McGwire: 65
    Sammy Sosa: 63
    Ken Griffy: 58
     */

    /*
    yaml:
    #Example 2.25. Unordered Sets
    # Sets are represented as a
    # Mapping where each key is
    # associated with a null value
    --- !!set
    ? Mark McGwire
    ? Sammy Sosa
    ? Ken Griff
     */
    private static void unorderedSets() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/07-unordered-sets.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Set<String> set = (Set<String>)yaml.load(inputStream);
        System.out.println(set);
        set.forEach(e -> {
            System.out.println(e);
        });
    }
    /*
    output:
    [Mark McGwire, Sammy Sosa, Ken Griff]
    Mark McGwire
    Sammy Sosa
    Ken Griff
     */


    /*
    yaml:
    #Example 2.22. Timestamps
    canonical: 2001-12-15T02:59:43.1Z
    iso8601: 2001-12-14t21:59:43.10-05:00
    spaced: 2001-12-14 21:59:43.10 -5
    date: 2002-12-14
     */
    private static void timestamps() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/04-timestamps.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<Object, Date> map = (Map<Object, Date>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    canonical: Fri Dec 14 18:59:43 PST 2001
    iso8601: Fri Dec 14 18:59:43 PST 2001
    spaced: Fri Dec 14 18:59:43 PST 2001
    date: Fri Dec 13 16:00:00 PST 2002
     */

    /*
    yaml:
    #Example 2.21. Miscellaneous
    null:
    booleans: [ true, false ]
    string: '012345'
     */
    private static void miscellaneous() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/03-miscellaneous.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<Object, Object> map = (Map<Object, Object>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    null: null
    booleans: [true, false]
    string: 012345
     */

    /*
    yaml:
    #Example 2.20. Floating Point
    canonical: 1.23015e+3
    exponential: 12.3015e+02
    fixed: 1230.15
    negative infinity: -.inf
    not a number: .NaN
     */
    private static void floatingPoint() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/02-floating-point.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, Float> map = (Map<String, Float>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    canonical: 1230.15
    exponential: 1230.15
    fixed: 1230.15
    negative infinity: -Infinity
    not a number: NaN
     */

    /*
    yaml:
    #Example 2.19. Integers
    canonical: 12345
    decimal: +12345
    octal: 0o14
    hexadecimal: 0xC
     */
    private static void integers() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/01-integers.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, Integer> map = (Map<String, Integer>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    canonical: 12345
    decimal: 12345
    octal: 0o14
    hexadecimal: 12
     */
}
