// Turn this project into a Scala.js project by importing these settings
enablePlugins(ScalaJSPlugin)

name := "Example"

version := "0.1-SNAPSHOT"

scalaVersion := "2.11.6"

scalacOptions ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8", // yes, this is 2 args
    "-feature",
    "-unchecked",
    "-Xfatal-warnings",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-value-discard",
    "-Xfuture"
)

// Apply default Scalariform formatting.
// Reformat at every compile.
// c.f. https://github.com/sbt/sbt-scalariform#advanced-configuration for more options.
scalariformSettings

scalastyleFailOnError := true

persistLauncher in Compile := true

persistLauncher in Test := false

testFrameworks += new TestFramework("utest.runner.Framework")

libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.0",
    "com.lihaoyi" %%% "scalatags" % "0.4.5",
    "com.lihaoyi" %%% "utest" % "0.3.0" % "test"
)
