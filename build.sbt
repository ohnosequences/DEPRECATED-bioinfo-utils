import AssemblyKeys._

Nice.javaProject

Nice.fatArtifactSettings

name := "bioinfo-util"

description := "Bioinformatics utility classes"

organization := "ohnosequences"

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "org.neo4j" % "neo4j" % "1.9.3"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.6.5"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.3"

libraryDependencies += "org.apache.commons" % "commons-math" % "2.1"

libraryDependencies += "org.jdom" % "jdom" % "2.0.2"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.3"

bucketSuffix := "era7.com"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "META-INF/CHANGES.txt" => MergeStrategy.first
    case "META-INF/LICENSES.txt" => MergeStrategy.first
    case x => old(x)
  }
}




