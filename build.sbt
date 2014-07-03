import AssemblyKeys._

Nice.javaProject

javaVersion := "1.8"

fatArtifactSettings

organization := "ohnosequences"

name := "bioinfo-util"

description := "Bioinformatics utility classes"

bucketSuffix := "era7.com"


libraryDependencies ++= Seq(
  "junit" % "junit" % "3.8.1" % "test",
  "com.amazonaws" % "aws-java-sdk" % "1.6.8",
  "org.apache.httpcomponents" % "httpclient" % "4.2",
  "org.apache.commons" % "commons-math" % "2.1",
  "org.jdom" % "jdom" % "2.0.2"
)

dependencyOverrides ++= Set(
  "commons-codec" % "commons-codec" % "1.6"
)

// fat jar assembly settings

assemblyOption in assembly ~= { _.copy(includeScala = false) }

mergeStrategy in assembly ~= { old => {
    case PathList("META-INF", "CHANGES.txt")                     => MergeStrategy.rename
    case PathList("META-INF", "LICENSES.txt")                    => MergeStrategy.rename
    case PathList("org", "apache", "commons", "collections", _*) => MergeStrategy.first
    case x                                                       => old(x)
  }
}
