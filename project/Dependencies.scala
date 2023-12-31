import sbt.*

object Dependencies {

  private val PekkoVersion = "1.0.2"
  private val PekkoConnectorsVersion = "1.0.1"

  val version = "1.0.0"
  val scalaVersion = "3.3.1"
  // pekko bash
  val pekko = List(
    "org.apache.pekko" %% "pekko-actor" % PekkoVersion,
    "org.apache.pekko" %% "pekko-stream" % PekkoVersion,
    "org.apache.pekko" %% "pekko-actor-typed" % PekkoVersion,
    "org.apache.pekko" %% "pekko-slf4j" % PekkoVersion)
  // other
  val commonsLang = "org.apache.commons" % "commons-lang3" % "3.14.0"

  // test
  val testContainer = "org.testcontainers" % "testcontainers" % "1.19.3" % Test
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % "2.0.5" % Test

  // jdbc driver
  val mysql8Driver = "mysql" % "mysql-connector-java" % "8.0.33"

  val jdbc = Seq(
    "org.scalikejdbc" %% "scalikejdbc" % "4.1.0",
    mysql8Driver)
}
