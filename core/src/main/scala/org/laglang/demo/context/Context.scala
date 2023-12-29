package org.laglang.demo.context
import java.util.concurrent.ConcurrentHashMap

object Context {

  private val settings = new ConcurrentHashMap[String, String]()

  implicit def convert(prop: Prop): String = settings.getOrDefault(prop.key, prop.default.get)

}

case class Prop(key: String, default: Option[String] = None)
