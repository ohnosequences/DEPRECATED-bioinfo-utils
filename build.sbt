import AssemblyKeys._

Nice.javaProject

javaVersion := "1.8"

fatArtifactSettings

organization := "ohnosequences"

name := "bioinfo-util"

description := "Bioinformatics utility classes"

bucketSuffix := "era7.com"

libraryDependencies ++= Seq(
  "org.neo4j" % "neo4j-kernel" % "1.9.3",
  "org.neo4j" % "neo4j" % "1.9.3",
  "com.amazonaws" % "aws-java-sdk" % "1.6.8",
  "org.apache.httpcomponents" % "httpclient" % "4.2",
  "org.apache.commons" % "commons-math" % "2.2",
  "org.jdom" % "jdom" % "2.0.2",
  "junit" % "junit" % "3.8.1" % "test"
)

// the aws lib conflicts with itself
dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.6"
)

// fat jar assembly settings
mainClass in assembly := Some("com.ohnosequences.BioinfoUtil")

assemblyOption in assembly ~= { _.copy(includeScala = false) }

mergeStrategy in assembly ~= { old => {
  case PathList("META-INF", "CHANGES.txt")                      => MergeStrategy.rename
  case PathList("META-INF", "LICENSES.txt")                     => MergeStrategy.rename
  case "log4j.properties"                                       => MergeStrategy.filterDistinctLines
  case PathList("org", "apache", "commons", "collections", _*)  => MergeStrategy.first
  case x                                                        => old(x)
}
}