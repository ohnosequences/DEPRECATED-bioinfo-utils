Nice.javaProject

name          := "bioinfo-util"
organization  := "ohnosequences"
description   := "Bioinformatics utility classes"

javaVersion := "1.8"

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpclient"   % "4.5.1",
  "org.apache.commons"        % "commons-math" % "2.2",
  "org.jdom"                  % "jdom"         % "2.0.2"
)

bucketSuffix := "era7.com"

fatArtifactSettings
mainClass in assembly := Some("com.ohnosequences.BioinfoUtil")
assemblyOption in assembly ~= { _.copy(includeScala = false) }
