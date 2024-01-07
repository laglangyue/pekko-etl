package org.laglang.demo.connector.internal

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.laglang.demo.catalog.{Row, SimpleRow}
import org.laglang.demo.connector.{Reader, Writer}

import scala.util.Random

class FakeConnector extends Reader {

  import FakeConnector.*

  /**
   * 返回一个 source
   *
   * @return
   */
  override def source: Source[Row, NotUsed] = {
    Source.fromIterator(() => LazyList(count).map(randomRow).iterator)
  }


  def randomRow(rowId: Int): Row = {
    SimpleRow(rowId, Random.nextString(10))
  }

}

object FakeConnector {
  val count = 1000
}
