package org.laglang.demo.connector.internal;

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.{Sink, SinkQueueWithCancel}
import org.laglang.demo.catalog.Row
import org.laglang.demo.connector.Writer
import org.reactivestreams.{Subscriber, Subscription};

class ConsoleConnector extends Writer {

  override def queue(): Sink[Row, SinkQueueWithCancel[Row]] = {
    Sink.queue()
  }

  override def sink(): Sink[Row, NotUsed] = {
    val sub: Subscriber[Row] = null
    Sink.fromSubscriber(sub)
  }
}

class ConsoleSubscribe extends Subscriber[Row] {

  override def onSubscribe(s: Subscription): Unit = {
    s.request(1)
  }

  override def onNext(t: Row): Unit = {
    System.out.println("Received data: " + t.toString)
  }

  override def onError(t: Throwable) = {
    t.printStackTrace()
  }


  override def onComplete(): Unit = {
  }
}
