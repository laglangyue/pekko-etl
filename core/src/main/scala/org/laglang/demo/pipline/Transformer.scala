package org.laglang.demo.pipline

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Flow
import org.laglang.demo.catalog.Row

trait Transformer {

  def flow: Flow[Row, Row, NotUsed]

}

