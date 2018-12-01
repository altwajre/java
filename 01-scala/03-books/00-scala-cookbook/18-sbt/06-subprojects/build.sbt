name := "multimoduleproject"

version := "0.1"

scalaVersion := "2.12.6"

lazy val common = project in file("common")

lazy val runner =
  (project in file("runner"))
  .dependsOn(common)
