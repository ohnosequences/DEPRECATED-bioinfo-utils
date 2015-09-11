Nice.javaProject

name := "bioinfo-util"
description := "Bioinformatics utility classes"
organization := "ohnosequences"

bucketSuffix := "era7.com"

javaVersion := "1.8"

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpclient"   % "4.5",
  "org.apache.commons"        % "commons-math" % "2.2",
  "org.jdom"                  % "jdom"         % "2.0.2"
)


fatArtifactSettings
mainClass in assembly := Some("com.ohnosequences.BioinfoUtil")
assemblyOption in assembly ~= { _.copy(includeScala = false) }
