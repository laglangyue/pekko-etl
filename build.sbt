import sbt.Keys.*
import sbt.*

ThisBuild / version := Dependencies.version

ThisBuild / scalaVersion := Dependencies.scala3

lazy val common = (project in file("common"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "common")

lazy val core = (project in file("core"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "core",
    libraryDependencies ++= Dependencies.pekko).dependsOn(common)

lazy val connector = (project in file("connector"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "core").dependsOn(common)

lazy val example = (project in file("example"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "core",
    libraryDependencies ++= Dependencies.pekko,
    libraryDependencies += Dependencies.commonsLang,
    libraryDependencies += Dependencies.slf4jSimple)
  .dependsOn(common)
  .dependsOn(core)
