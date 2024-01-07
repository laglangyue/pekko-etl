package org.laglang.demo.connector.internal;

import org.apache.pekko.Done
import org.apache.pekko.stream.scaladsl.Sink
import org.laglang.demo.catalog.Row
import org.laglang.demo.connector.Writer
import org.reactivestreams.Subscriber

import scala.concurrent.Future;

class ConsoleConnector extends Writer {
  override def sink(): Sink[Row, Future[Done]] = {
    val sub: Subscriber[Row] = null
    Sink.foreach(row => println(row.toString))
  }


  //  override def sink(): Sink[Row, NotUsed] = {
  //    //    Sink.foreach(row => println(row.toString))
  //    val sub: Subscriber[Row] = null
  //    Sink.fromSubscriber(sub)
  //  }
}
