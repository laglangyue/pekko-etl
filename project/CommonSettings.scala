import sbt.Keys.*
import sbt.*

object CommonSettings {
  lazy val commonSettings = Seq(
    organization := "org.laglang.demo",
    version := Dependencies.version,
    scalaVersion := Dependencies.scalaVersion,
    scalacOptions ++= CompileOptions.compileOptions,
    Test / parallelExecution := false,
    Test / logBuffered := false,
    ThisBuild / parallelExecution := false,
    GlobalScope / parallelExecution := false)
}
