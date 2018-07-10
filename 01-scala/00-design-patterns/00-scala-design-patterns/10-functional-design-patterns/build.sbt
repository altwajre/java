name := "10-functional-design-patterns"

version := "0.1"

scalaVersion := "2.12.6"

val monocleVersion = "1.5.0" // 1.5.0-cats based on cats 1.0.x

libraryDependencies ++= Seq(
  "org.scalaz" %% "scalaz-core" % "7.2.20",
  "com.h2database" % "h2" % "1.4.197",
  "com.github.julien-truffaut" %%  "monocle-core"  % monocleVersion,
  "com.github.julien-truffaut" %%  "monocle-macro" % monocleVersion,
  "org.scalatest" %% "scalatest" % "3.0.5",
  "org.mockito" % "mockito-all" % "1.10.19",
  "junit" % "junit" % "4.12"
)
