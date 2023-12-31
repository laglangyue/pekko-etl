package org.laglang.demo.connector

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.connectors.slick.scaladsl.{SlickSession, Slick}
import org.apache.pekko.stream.scaladsl.Source
import slick.jdbc.JdbcBackend.Database
import org.laglang.demo.connector.JdbcOption._

import java.util.Properties
import org.laglang.demo.context.Context.convert
import org.laglang.demo.table.Row

class TableReader {
  private val database = Database.forURL(Url, User, Password, new Properties(), Driver)

  private val dialect = DialectFactory.getDialect(DialectOption)
  implicit val session: SlickSession = SlickSession.forDbAndProfile(database, dialect.profile)
  import dialect.profile.api._

  def reader():Source[Row, NotUsed] = {
    val query = sql"select * from source"
    Source.fromPublisher(session.db.stream(query))
  }

}
