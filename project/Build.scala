import sbt.Keys._
import sbt._

/** Adds common settings automatically to all subprojects */
object Build extends AutoPlugin {

  object autoImport {
    val AvroVersion = "1.9.2"
    val Log4jVersion = "1.2.17"
    val ScalatestVersion = "3.1.1"
    val Slf4jVersion = "1.7.30"
    val Json4sVersion = "3.6.7"
    val CatsVersion = "2.1.1"
    val ShapelessVersion = "2.3.3"
    val RefinedVersion = "0.9.14"
    val MagnoliaVersion = "0.14.5"
    val ScalaMeterVersion = "0.19"
  }

  import autoImport._

  def isTravis = System.getenv("TRAVIS") == "true"
  def travisBuildNumber = System.getenv("TRAVIS_BUILD_NUMBER")

  override def trigger = allRequirements
  override def projectSettings = Seq(
    organization := "com.47deg",
    scalaVersion := "2.12.11",
    crossScalaVersions := Seq("2.12.11", "2.13.2"),
    resolvers += Resolver.mavenLocal,
    parallelExecution in Test := false,
    scalacOptions := Seq(
      "-unchecked", "-deprecation",
      "-encoding",
      "utf8",
   //   "-Xfatal-warnings",
      "-feature",
      "-language:higherKinds",
   //   "-Xlog-implicits",
      "-language:existentials"
    ),
    javacOptions := Seq("-source", "1.8", "-target", "1.8"),
    libraryDependencies ++= Seq(
      "org.scala-lang"    % "scala-reflect"     % scalaVersion.value,
      "org.scala-lang"    % "scala-compiler"    % scalaVersion.value,
      "org.apache.avro"   % "avro"              % AvroVersion,
      "org.slf4j"         % "slf4j-api"         % Slf4jVersion          % "test",
      "log4j"             % "log4j"             % Log4jVersion          % "test",
      "org.slf4j"         % "log4j-over-slf4j"  % Slf4jVersion          % "test",
      "org.scalatest"     %% "scalatest"        % ScalatestVersion      % "test"
    )
  )

}
