package org.laglang.demo.connector

import org.apache.pekko.{Done, NotUsed}
import org.apache.pekko.stream.scaladsl.Sink
import org.laglang.demo.catalog.Row

import scala.concurrent.Future

trait Writer {

  def sink(): Sink[Row, Future[Done]]

}
