package org.laglang.demo.connector

import org.laglang.demo.catalog.StructType
import org.laglang.demo.connector.dialect.MysqlDialect

trait Dialect {

  def getSchema(): StructType = {
    StructType.apply(Array())
  }
}

object DialectFactory {

  def getDialect(dialect: String): Dialect = {
    dialect match {
      case "mysql" => MysqlDialect()
    }
  }


}
