package org.laglang.demo.connector
import org.apache.pekko.stream.connectors.slick.javadsl.SlickSession
import org.apache.pekko.stream.connectors.slick.scaladsl.Slick
import org.apache.pekko.stream.scaladsl.Source
import slick.jdbc.JdbcBackend.Database
import org.laglang.demo.connector.JdbcOption._

import java.util.Properties
import org.laglang.demo.context.Context.convert
import slick.jdbc.GetResult

class Reader {
  val database = Database.forURL(Url, User, Password, new Properties(), Driver)

  val dialect = DialectFactory.getDialect(DialectOption)

  import dialect.profile.api._

  def reader() = {
    implicit val convert = GetResult(r => (r.nextInt(), r.nextString()))
    val query = sql"select * from source".as
    Source.fromPublisher(database.stream(query))
  }

}
