import Dependencies._

ThisBuild / organization := "com.example"
ThisBuild / scalaVersion := "2.13.10"
addCompilerPlugin("org.scalameta" % "semanticdb-scalac" % "4.7.8" cross CrossVersion.full)
enablePlugins(
  JavaAppPackaging,
  DockerPlugin
)


lazy val root =
  (project in file("."))
  .settings(
    name := "github-stats-sample",
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-unchecked",
      "-language:postfixOps",
      "-language:higherKinds", // HKT required for Monads and other HKT types
      // "-Wunused", // for scalafix
      "-Yrangepos", // required for semantic db
    ),
    run / fork := true,
    Compile / mainClass := Some("com.example.MainApp"),
    Docker / packageName := "esumitra/github-stats-service",
    dockerBaseImage := "openjdk:jre",
    dockerExposedPorts ++= Seq(8080),
    dockerEnvVars ++= Map(("GH_API_KEY", "xxx")),
    dockerExposedVolumes := Seq("/opt/docker/.logs", "/opt/docker/.keys"),
    libraryDependencies ++= Dependencies.core ++ Dependencies.scalaTest,
    assembly / mainClass := Some("com.example.MainApp"),
    assembly / assemblyJarName := "github-stats-sample.jar",
    assembly / test := {},
    assembly / assemblyMergeStrategy := {
      case PathList("META-INF", xs @ _*) => MergeStrategy.discard
      case "application.conf"            => MergeStrategy.concat
      case x =>
        val oldStrategy = (assembly / assemblyMergeStrategy).value
        oldStrategy(x)
    }
  )
