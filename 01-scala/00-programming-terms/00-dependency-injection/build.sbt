name := "dependency-injection"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.h2database" % "h2" % "1.4.197",
  "org.scalatest" %% "scalatest" % "3.0.5",
  "org.mockito" % "mockito-all" % "1.10.19",
  "junit" % "junit" % "4.12"
)
