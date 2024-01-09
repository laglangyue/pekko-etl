package org.laglang.demo.connector.demo

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.laglang.demo.catalog.{Row, SimpleRow}
import org.laglang.demo.connector.reader.Reader

import scala.util.Random

/**
 * demo测试
 */
class FakeConnector extends Reader {

  import FakeConnector.*

  /**
   * 返回一个 source
   *
   * @return
   */
  override def source: Source[Row, NotUsed] = {
    Source.fromIterator(() => LazyList.from(1, 1).take(count).map(randomRow).iterator)
  }


  def randomRow(rowId: Int): Row = {
    SimpleRow(rowId, Random.nextPrintableChar())
  }

}

object FakeConnector {
  val count = 1000
}
