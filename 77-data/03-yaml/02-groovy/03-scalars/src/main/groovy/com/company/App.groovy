package com.company

import org.yaml.snakeyaml.Yaml

//http://www.yaml.org/spec/1.2/spec.html - Scalars
// Scalar content can be written in block notation, using a literal style (indicated by "|") where all line breaks are
// significant. Alternatively, they can be written with the folded style (denoted by ">") where each line break is folded
// to a space unless it ends an empty or a more-indented line
class App {
    static void main(String... args){

        // #Example 2.13.  In literals,  newlines are preserved
        InputStream inputStream = new FileInputStream(new File("src/main/resources/spec/01-literals-new-lines-preserved.yaml"))

        def literalsNewLinesPreserved = new Yaml().load(inputStream)
        println literalsNewLinesPreserved
// \//||\/||
// // ||  ||__

        // #Example 2.14.  In the folded scalars, newlines become spaces
        def foldedScalarsNewlinesBecomesSpaces = '''
--- >
  Mark McGwire's
  year was crippled
  by a knee injury.
'''

        def newlinesBecomesSpaces = new Yaml().load(foldedScalarsNewlinesBecomesSpaces)
        println newlinesBecomesSpaces
//        Mark McGwire's year was crippled by a knee injury.

        // #Example 2.15.  Folded newlines are preserved for "more indented" and blank lines
        def foldedNewlinesArePreserved = '''
>
  Sammy Sosa completed another
  fine season with great stats.
  
    63 Home Runs
    0.288 Batting Average
    
  What a year!
'''

        def foldedNewlinesPreserved = new Yaml().load(foldedNewlinesArePreserved)
        println foldedNewlinesPreserved
//        Sammy Sosa completed another fine season with great stats.
//
//        63 Home Runs
//        0.288 Batting Average
//
//        What a year!

        // #Example 2.16.  Indentation determines scope
        def indentationDeterminesScope = '''
name: Mark McGwire
accomplishment: >
  Mark set a major league
  home run record in 1998
stats: |
  65 Home Runs
  0.278 Batting Average
'''

        def indentationScope = new Yaml().load(indentationDeterminesScope)
        println indentationScope
//        [name:Mark McGwire, accomplishment:Mark set a major league home run record in 1998
//         , stats:65 Home Runs
//         0.278 Batting Average
//        ]


        /*
        yaml:
        #Example 2.17. Quoted Scalars
        unicode: "Sosa did fine.\u263A"
        control: "\b1998\t1999\t2000\n"
        hex esc: "\x0d\x0a is \r\n"

        single: '"Howdy!" he cried.'
        quoted: ' # Not a ''comment''.'
        tie-fighter: '|\-*-/|'
         */
        inputStream = new FileInputStream(new File("src/main/resources/spec/05-quoted-scalars.yaml"))

        def quotedScalars = new Yaml().load(inputStream)
        println quotedScalars
        /*
        output:
        [unicode:Sosa did fine.☺, control1998	1999	2000
         , hex esc:
                 is
         , single:"Howdy!" he cried., quoted: # Not a 'comment'., tie-fighter:|\-*-/|]
         */

        // #Example 2.18. Multi-line Flow Scalars
        def multiLineFlowScalars = '''
plain:
  This unquoted scalar
  spans many lines
  
quoted: "So does this
  quoted scalar.\n"
'''

        def multiLineScalars = new Yaml().load(multiLineFlowScalars)
        println multiLineScalars
//        [plain:This unquoted scalar spans many lines, quoted:So does this quoted scalar. ]

    }
}
