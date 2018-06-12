name := "scheduling-utilities"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.16",
  "org.scalatest" %% "scalatest" % "3.2.0-SNAP10"
)
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.5.13" % Test
