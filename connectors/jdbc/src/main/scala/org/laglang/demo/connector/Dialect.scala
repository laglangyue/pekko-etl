package org.laglang.demo.connector

import org.laglang.demo.connector.dialect.MysqlDialect
import slick.jdbc.JdbcProfile

trait Dialect {
  val profile: JdbcProfile
}

object DialectFactory {

  def getDialect(dialect: String): Dialect = {
    dialect match {
      case "mysql" => MysqlDialect()
    }
  }

}
