# gatling - scala

- Ability to deal with logic between requests
- Gatling implements virtual users as messages instead of threads which scales much better

https://gatling.io/docs/current/cheat-sheet/
https://gatling.io/documentation/
https://gatling.io/docs/current/quickstart/

- cheatsheet

https://worldline.github.io/gatling-cheatsheet/

- maven - intellij

https://gatling.io/docs/current/extensions/maven_archetype/

- github

https://github.com/gatling/gatling

- Jenkins

https://wiki.jenkins.io/display/JENKINS/Gatling+Plugin

## Recorder Installation

download `bundle` at https://gatling.io/download/
unzip it to `/Users/whan`
launch it `/Users/whan/gatling/bin/recorder.sh`

## tutorial

> gatling tutorial

https://www.safaribooksonline.com/library/view/building-microservices-with/9781788292658/video2_1.html

> youtube

https://www.youtube.com/watch?v=fqP6PTUdtkY&list=PLd4gvNaNZ4T3NCWsv3zwHYlLGtr9s1-Fz

> feeders

https://www.rubix.nl/blogs/basic-gatling-load-script-feeders

## KB

- conflict between Gatling's Expression Language and Scala's String Interpolation

https://groups.google.com/forum/#!topic/gatling/Wd8TJISeWnE

You have a conflict between Gatling's Expression Language (used with a ${}), and Scala's String Interpolation (also ${} and $, completed with the s"" macro).

There is a way to use both, but this is a bit tricky:
Prefix the string with s
Escape Gatling's Expression Language such that it won't be interpreted by Scala as an interpolation
Typically, double the $ that are Gatling's EL you don't want to be interpolated, as such:

StringBody(s"""{"objectId":$${assetid},"objectType":"m-asset","name1": "$tName","accountId":4,"userId":5}""")
