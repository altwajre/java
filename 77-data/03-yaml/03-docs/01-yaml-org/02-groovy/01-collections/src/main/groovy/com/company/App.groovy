package com.company

import org.yaml.snakeyaml.Yaml

/*
Collections

http://www.yaml.org/spec/1.2/spec.html#id2759963

 */
class App {
    static void main(String... args) {

        // #Example 2.1.  Sequence of Scalars (ball players)
        def sequenceOfScalars = '''
- Mark McGwire
- Sammy Sosa
- Ken Griffey
'''
        List<String> sequence = new Yaml().load(sequenceOfScalars)

        println sequence
//        [Mark McGwire, Sammy Sosa, Ken Griffey]

        // #Example 2.2.  Mapping Scalars to Scalars (player statistics)
        def mapScalarsToScalars = '''
hr:  65    # Home runs
avg: 0.278 # Batting average
rbi: 147   # Runs Batted In
'''
        Map<Integer, String> map = new Yaml().load(mapScalarsToScalars)

        println map
//        [hr:65, avg:0.278, rbi:147]

        // #Example 2.3.  Mapping Scalars to Sequences (ball clubs in each league)
        def mapScalarsToSequences = '''
american:
  - Boston Red Sox
  - Detroit Tigers
  - New York Yankees
national:
  - New York Mets
  - Chicago Cubs
  - Atlanta Braves
'''
        def mapSequences = new Yaml().loadAll(mapScalarsToSequences)
        mapSequences.each {println it}
//        [american:[Boston Red Sox, Detroit Tigers, New York Yankees], national:[New York Mets, Chicago Cubs, Atlanta Braves]]

        // #Example 2.4.  Sequence of Mappings (players’ statistics)
        def sequenceOfMap = '''
-
  name: Mark McGwire
  hr:   65
  avg:  0.278
-
  name: Sammy Sosa
  hr:   63
  avg:  0.288
'''

        def sequenceMap = new Yaml().loadAll(sequenceOfMap)
        sequenceMap.each {println it}
//        [[name:Mark McGwire, hr:65, avg:0.278], [name:Sammy Sosa, hr:63, avg:0.288]]

        // #Example 2.5. Sequence of Sequences
        def sequenceOfSequences = '''
- [name        , hr, avg  ]
- [Mark McGwire, 65, 0.278]
- [Sammy Sosa  , 63, 0.288]
'''

        def sequenceSequences = new Yaml().loadAll(sequenceOfSequences)
        sequenceSequences.each{println it}
//        [[name, hr, avg], [Mark McGwire, 65, 0.278], [Sammy Sosa, 63, 0.288]]

        // #Example 2.6. Mapping of Mappings
        def mapOfMaps = '''
Mark McGwire: {hr: 65, avg: 0.278}
Sammy Sosa: {
    hr:  63,
    avg: 0.288
  }
'''
        def mapMaps = new Yaml().loadAll(mapOfMaps)
        mapMaps.each {println it}
//        [Mark McGwire:[hr:65, avg:0.278], Sammy Sosa:[hr:63, avg:0.288]]
    }
}
