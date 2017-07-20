package com.company.app;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class App
{
    public static void main( String[] args ) throws Exception
    {
        literalStyleNewlinesArePreserved();

        foldedStyleNewlinesBecomeSpaces();

        foldedNewlinesArePreserved();

        indentationDeterminesScope();

        quotedScalars();

        multilineFlowScalars();
    }

    /*
    yaml:
    #Example 2.18. Multi-line Flow Scalars
    plain:
      This unquoted scalar
      spans many lines.

    quoted: "So does this
      quoted scalar.\n"
     */
    private static void multilineFlowScalars() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/06-multi-line-flow-scalars.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, String> map = (Map<String, String>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    plain: This unquoted scalar spans many lines.
    quoted: So does this quoted scalar.
     */

    /*
    YAML's flow scalars include the plain style and two quoted styles.
    The double-quoted style provides escape sequences.
    The single-quoted style is useful when escaping is not needed.
    All flow scalars can span multiple lines; line breaks are always folded.

    yaml:
    #Example 2.17. Quoted Scalars
    unicode: "Sosa did fine.\u263A"
    control: "\b1998\t1999\t2000\n"
    hex esc: "\x0d\x0a is \r\n"

    single: '"Howdy!" he cried.'
    quoted: ' # Not a ''comment''.'
    tie-fighter: '|\-*-/|'
     */
    private static void quotedScalars() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/05-quoted-scalars.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, String> map = (Map<String, String>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    unicode: Sosa did fine.☺
    control:1998	1999	2000

    hex esc:
     is

    single: "Howdy!" he cried.
    quoted:  # Not a 'comment'.
    tie-fighter: |\-*-/|
     */

    /*
    yaml:
    #Example 2.16.  Indentation determines scope
    name: Mark McGwire
    accomplishment: >
      Mark set a major league
      home run record in 1998.
    stats: |
      65 Home Runs
      0.278 Batting Average
     */
    private static void indentationDeterminesScope() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/04-indentation-determines-scope.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        Map<String, String> map = (Map<String, String>)yaml.load(inputStream);
        map.entrySet().forEach(e -> {
            System.out.println(e.getKey() + ": " + e.getValue());
        });
    }
    /*
    output:
    name: Mark McGwire
    accomplishment: Mark set a major league home run record in 1998.

    stats: 65 Home Runs
    0.278 Batting Average
     */

    /*
    yaml:
    #Example 2.15.  Folded newlines are preserved
    #for "more indented" and blank lines
    >
     Sammy Sosa completed another
     fine season with great stats.

       63 Home Runs
       0.288 Batting Average

     What a year!
     */
    private static void foldedNewlinesArePreserved() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/03-folded-newlines-are-preserved.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        String scalar = (String)yaml.load(inputStream);
        System.out.println(scalar);
    }
    /*
    output:
    Sammy Sosa completed another fine season with great stats.

      63 Home Runs
      0.288 Batting Average

    What a year!
     */

    /*
    Alternatively, they can be written with the folded style (denoted by ">")
    where each line break is folded to a space unless it ends an empty or a more-indented line.

    yaml:
    #Example 2.14.  In the folded scalars,
    #newlines become spaces
    --- >
      Mark McGwire's
      year was crippled
      by a knee injury.
     */
    private static void foldedStyleNewlinesBecomeSpaces() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/02-folded-style-newlines-become-spaces.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        String scalar = (String)yaml.load(inputStream);
        System.out.println(scalar);
    }
    /*
    output:
    Mark McGwire's year was crippled by a knee injury.
     */

    /*
    Scalar content can be written in block notation, using a literal style (indicated by "|")
    where all line breaks are significant.

    yaml:
    #Example 2.13.  In literals,
    #newlines are preserved
    # ASCII Art
    --- |
      \//||\/||
      // ||  ||__
     */
    private static void literalStyleNewlinesArePreserved() throws FileNotFoundException {

        String pathname = "src/main/resources/spec/01-literal-style-newlines-are-preserved.yaml";
        InputStream inputStream = new FileInputStream(new File(pathname));
        Yaml yaml = new Yaml();
        String scalar = (String)yaml.load(inputStream);
        System.out.println(scalar);
    }
    /*
    output:
    \//||\/||
    // ||  ||__
     */
}
