package org.laglang.demo.connector

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Source
import org.laglang.demo.catalog.Row

trait Reader {

  /**
   * 返回一个 source
   * @return
   */
  def source: Source[Row, NotUsed]
}
