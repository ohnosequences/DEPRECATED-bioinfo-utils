import AssemblyKeys._

Nice.javaProject

Nice.fatArtifactSettings

name := "bioinfo-util"

description := "Bioinformatics utility classes"

organization := "ohnosequences"

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "org.neo4j" % "neo4j" % "1.9.3"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.0.004"

libraryDependencies += "commons-httpclient" % "commons-httpclient" % "3.1"

libraryDependencies += "org.apache.commons" % "commons-math" % "2.1"

libraryDependencies += "jdom" % "jdom" % "1.1"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.3"

bucketSuffix := "era7.com"

mergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case "META-INF/CHANGES.txt" => MergeStrategy.first
    case "META-INF/LICENSES.txt" => MergeStrategy.first
    case x => old(x)
  }
}




