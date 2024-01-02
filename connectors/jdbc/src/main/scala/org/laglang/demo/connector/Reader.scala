package org.laglang.demo.connector

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.connectors.slick.scaladsl.{Slick, SlickSession}
import org.apache.pekko.stream.scaladsl.Source
import slick.jdbc.JdbcBackend.Database
import org.laglang.demo.connector.JdbcOption._

import java.util.Properties
import org.laglang.demo.context.Context.convert
import org.laglang.demo.catalog.{StructType, Row, StructField}
import org.laglang.demo.row.BooleanType
import slick.jdbc.meta.MTable
import slick.jdbc.{GetResult, PositionedResult}
import slick.lifted.TableQuery
import slick.model.Table

import javax.sql.rowset.JdbcRowSet

class TableReader {
  private val database = Database.forURL(Url, User, Password, new Properties(), Driver)

  private val dialect = DialectFactory.getDialect(DialectOption)
  implicit val session: SlickSession = SlickSession.forDbAndProfile(database, dialect.profile)
  import dialect.profile.api._

  private val schema: StructType = dialect.getSchema()

  lazy val sql = sql"select * from source".as[schema]

  def stream(): Source[Row, NotUsed] = {
    Slick.source(sql)
  }

}

object TableReader {
  def result(schema: StructType) = {
    GetResult {
      r=>
    }
  }

  def dataTypeMap(structField: StructField): PositionedResult => Any = {
    structField.dataType match {
      case BooleanType => r: PositionedResult => r.nextBoolean()
      case _ => r: PositionedResult => r.nextObject()
    }
  }

}
