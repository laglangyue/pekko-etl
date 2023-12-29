package org.laglang.demo.connector
import org.laglang.demo.context.Prop

object JdbcOption {

  val Url = Prop("reader.jdbc.url")
  val User = Prop("reader.jdbc.user")
  val Password = Prop("reader.jdbc.password")
  val Driver = Prop("reader.jdbc.driver")
  val DialectOption = Prop("reader.jdbc.dialect")

}
