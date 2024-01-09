package org.laglang.demo.connector.writer

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.Status.Success
import org.apache.pekko.stream.scaladsl.Flow
import org.laglang.demo.catalog.Row

trait Writer {

  def sink(): Flow[Row, Commit, NotUsed]

}
