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
    "eu.timepit" %% "refined"                 % "0.11.0",
    "eu.timepit" %% "refined-cats"            % "0.11.0", // optional
    "eu.timepit" %% "refined-eval"            % "0.11.0", // optional, JVM-only
    "eu.timepit" %% "refined-jsonpath"        % "0.11.0", // optional, JVM-only
    "eu.timepit" %% "refined-pureconfig"      % "0.11.0", // optional, JVM-only
    "eu.timepit" %% "refined-scalacheck"      % "0.11.0", // optional
    "eu.timepit" %% "refined-scalaz"          % "0.11.0", // optional
    "eu.timepit" %% "refined-scodec"          % "0.11.0", // optional
    "eu.timepit" %% "refined-scopt"           % "0.11.0", // optional
    "eu.timepit" %% "refined-shapeless"       % "0.11.0",  // optional
    
    // http
    "com.softwaremill.sttp.tapir" %% "tapir-core" % "1.6.0",
    "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "1.6.0",
    "com.softwaremill.sttp.tapir" %% "tapir-sttp-client" % "1.6.0",
    // "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "1.6.0",
    "com.softwaremill.sttp.tapir" %% "tapir-akka-http-server" % "1.6.0",

    // logging
    "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
    "ch.qos.logback" % "logback-classic" % "1.2.3"
  )
}
