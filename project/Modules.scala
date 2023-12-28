import sbt.*
import sbt.Keys.*

case class Connector(name: String, dependencies: Seq[ModuleID])

object Modules {
  def toConnector(module: Connector): Project = {
    val moduleName = module.name.split("/").last

    Project(moduleName, file(module.name))
      .settings(CommonSettings.commonSettings)
      .settings(
        name := moduleName,
        libraryDependencies ++= module.dependencies)
  }
}
