package org.laglang.demo.connector

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.{Sink, SinkQueueWithCancel}
import org.laglang.demo.catalog.Row

trait Writer {

  def queue(): Sink[Row, SinkQueueWithCancel[Row]]

  def sink(): Sink[Row, NotUsed]

}
