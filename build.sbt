import sbt.Keys.*
import sbt.*

ThisBuild / version := Dependencies.version

ThisBuild / scalaVersion := Dependencies.scalaVersion

lazy val common = (project in file("common"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "common")

lazy val core = (project in file("core"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "core",
    libraryDependencies ++= Dependencies.pekko).dependsOn(common)



lazy val example = (project in file("example"))
  .settings(CommonSettings.commonSettings *)
  .settings(
    name := "core",
    libraryDependencies ++= Dependencies.pekko,
    libraryDependencies += Dependencies.commonsLang,
    libraryDependencies += Dependencies.slf4jSimple)
  .dependsOn(common)
  .dependsOn(core)

lazy val connectors = (project in file("connectors"))
  .aggregate(jdbc)
  .dependsOn(core)
  .settings(
    name := "connectors")

lazy val jdbc = Modules.toConnector(Connector("connectors/jdbc", Dependencies.jdbc))
  .dependsOn(core)
