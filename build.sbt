
Nice.javaProject


name := "bioinfo-util"

description := "Bioinformatics utility classes"

organization := "ohnosequences"

libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += "org.neo4j" % "neo4j" % "1.9.3"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.6.5"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.2"

libraryDependencies += "org.apache.commons" % "commons-math" % "2.1"

libraryDependencies += "org.jdom" % "jdom" % "2.0.2"

dependencyOverrides += "commons-codec" % "commons-codec" % "1.3"

bucketSuffix := "era7.com"





