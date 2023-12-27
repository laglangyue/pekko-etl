import sbt.*

object Dependencies {

  private val PekkoVersion = "1.0.2"
  private val PekkoConnectorsVersion = "1.0.1"

  val version = "1.0.0"
  val scala3 = "3.3.1"
  val pekko = List(
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion)
  val testContainer = "org.testcontainers" % "testcontainers" % "1.19.3" % Test

  val commonsLang = "org.apache.commons" % "commons-lang3" % "3.14.0"
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % "2.0.5" % Test

}
