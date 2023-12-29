package org.laglang.demo.connector.dialect
import org.laglang.demo.connector.Dialect
import slick.jdbc.JdbcProfile

class MysqlDialect extends Dialect {
   val profile: JdbcProfile = slick.jdbc.MySQLProfile
}

object MysqlDialect {
  def apply(): MysqlDialect = new MysqlDialect(

  )
}
