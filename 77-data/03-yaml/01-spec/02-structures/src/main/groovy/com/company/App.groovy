package com.company

import org.yaml.snakeyaml.Yaml

class App {
    static void main(String... args){

        // #Example 2.7.  Two Documents in a Stream (each with a leading comment)
        def twoDocumentsInStream = '''
# Ranking of 1998 home runs
---
- Mark McGwire
- Sammy Sosa
- Ken Griffey

# Team ranking
---
- Chicago Cubs
- St Louis Cardinals
'''
        def twoDocuments = new Yaml().loadAll(twoDocumentsInStream)
        twoDocuments.each {println it}
//        [Mark McGwire, Sammy Sosa, Ken Griffey]
//        [Chicago Cubs, St Louis Cardinals]

        // #Example 2.8.  Play by Play Feed from a Game
        def playByPalyFeedFromGame = '''
---
time: 20:03:20
player: Sammy Sosa
action: strike (miss)
...
---
time: 20:03:47
player: Sammy Sosa
action: grand slam
'''
        def playByPlay = new Yaml().loadAll(playByPalyFeedFromGame)
        playByPlay.each {println it}
//        [time:72200, player:Sammy Sosa, action:strike (miss)]
//        [time:72227, player:Sammy Sosa, action:grand slam]


        // #Example 2.9.  Single Document with Two Comments
        def singleDocumentWithTwoComments = '''
---
hr: # 1998 hr ranking
  - Mark McGwire
  - Sammy Sosa
rbi:
  # 1998 rbi ranking
  - Sammy Sosa
  - Ken Griffey
'''
        def singleDocTwoComments = new Yaml().loadAll(singleDocumentWithTwoComments)
        singleDocTwoComments.each {println it}
//        [hr:[Mark McGwire, Sammy Sosa], rbi:[Sammy Sosa, Ken Griffey]]

        // #Example 2.10.  Node for “Sammy Sosa” appears twice in this document
        def nodeAppearsTwiceInDocument = '''
---
hr:
  - Mark McGwire
  # Following node labeled SS
  - &SS Sammy Sosa
rbi:
  - *SS # Subsequent occurrence
  - Ken Griffey
'''
        def nodeAppearsTwice = new Yaml().loadAll(nodeAppearsTwiceInDocument)
        nodeAppearsTwice.each {println it}
//        [hr:[Mark McGwire, Sammy Sosa], rbi:[Sammy Sosa, Ken Griffey]]

        // ###A question mark and space ("? ") indicate a complex mapping key.
        // Within a block collection, key: value pairs can start immediately following the dash, colon or question mark.

        // #Example 2.11. Mapping between Sequences
        def mapBetweenSequences = '''
? - Detroit Tigers
  - Chicago cubs
:
  - 2001-07-23
  
? [ New York Yankees,
    Atlanta Braves ]
: [ 2001-07-02, 2001-08-12, 
    2001-08-14 ]
'''

        def mapBetweenSeqs = new Yaml().loadAll(mapBetweenSequences)
        mapBetweenSeqs.each {println it}
//        [[Detroit Tigers, Chicago cubs]:[Sun Jul 22 17:00:00 PDT 2001], [New York Yankees, Atlanta Braves]:[Sun Jul 01 17:00:00 PDT 2001, Sat Aug 11 17:00:00 PDT 2001, Mon Aug 13 17:00:00 PDT 2001]]

        // #Example 2.12. Compact Nested Mapping
        def compactNestedMap = '''
---
# Products purchased
- item    : Super Hoop
  quantity: 1
- item    : Basketball
  quantity: 4
- item    : Big Shoes
  quantity: 1
'''
        def compactMap = new Yaml().loadAll(compactNestedMap)
        compactMap.each {println it}
//        [[item:Super Hoop, quantity:1], [item:Basketball, quantity:4], [item:Big Shoes, quantity:1]]

    }
}
