Nice.javaProject


name := "bioinfo-util"

description := "Bioinformatics utility classes"

organization := "ohnosequences"

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "junit" % "junit" % "3.8.1" % "test",
  "org.neo4j" % "neo4j" % "1.9.3",
  "com.amazonaws" % "aws-java-sdk" % "1.6.8",
  "org.apache.httpcomponents" % "httpclient" % "4.2",
  "org.apache.commons" % "commons-math" % "2.1",
  "org.jdom" % "jdom" % "2.0.2"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.6"
)
