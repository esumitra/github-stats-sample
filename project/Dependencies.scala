import sbt._

object Dependencies {
  lazy val scalaTest = Seq(
    "org.scalatest" %% "scalatest" % "3.2.13" % Test,
    "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0" % Test,
    "com.github.alexarchambault" %% "scalacheck-shapeless_1.15" % "1.3.0" % Test
  )

  val circeVersion = "0.14.1"
  val pureconfigVersion = "0.17.4"
  val catsVersion = "2.9.0"
  val refinedVersion = "0.11.0"
  val tapirVersion = "1.6.0"

  lazy val core = Seq(
    // cats FP libary
    "org.typelevel" %% "cats-core" % catsVersion,

    // support for JSON formats
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
    "io.circe" %% "circe-literal" % circeVersion,

    // support for typesafe configuration
    "com.github.pureconfig" %% "pureconfig" % pureconfigVersion,

    // modeling
    "io.scalaland" %% "chimney" % "0.7.5",
    "com.softwaremill.quicklens" %% "quicklens" % "1.9.4",

    // refined types
    "eu.timepit" %% "refined"                 % refinedVersion,
    "eu.timepit" %% "refined-cats"            % refinedVersion, // optional
    "eu.timepit" %% "refined-eval"            % refinedVersion, // optional, JVM-only
    "eu.timepit" %% "refined-jsonpath"        % refinedVersion, // optional, JVM-only
    "eu.timepit" %% "refined-pureconfig"      % refinedVersion, // optional, JVM-only
    "eu.timepit" %% "refined-scalacheck"      % refinedVersion, // optional
    "eu.timepit" %% "refined-scalaz"          % refinedVersion, // optional
    "eu.timepit" %% "refined-scodec"          % refinedVersion, // optional
    "eu.timepit" %% "refined-scopt"           % refinedVersion, // optional
    "eu.timepit" %% "refined-shapeless"       % refinedVersion,  // optional
    
    // opaque types / new types
    "io.estatico" %% "newtype" % "0.4.4",
    
    // REST API and http server
    "com.softwaremill.sttp.tapir" %% "tapir-core" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % tapirVersion,
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % tapirVersion,

    // logging
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
