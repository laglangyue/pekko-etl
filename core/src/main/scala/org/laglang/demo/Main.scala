package org.laglang.demo

import org.apache.pekko.NotUsed
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.scaladsl.{Sink, Source}
import org.laglang.demo.catalog.Row
import org.laglang.demo.connector.demo.{ConsoleConnector, FakeConnector}
import org.laglang.demo.connector.reader.Reader
import org.laglang.demo.connector.writer.Writer
import org.laglang.demo.pipline.Transformer

object Main {

  implicit val system: ActorSystem = ActorSystem("QuickStart")

  // pipeline test
  def main(args: Array[String]): Unit = {
    val reader: Reader = FakeConnector()
    val writer: Writer = ConsoleConnector()
    val transformers: Iterator[Transformer] = List().iterator
    val source: Source[Row, NotUsed] = reader.source
    val flow: Source[Row, NotUsed] = transformers.foldLeft(source)((s, t) => source.via(t.flow))
    val sink = writer.sink()
    val commit = source.via(sink)
    val done = commit.runWith(Sink.ignore)
    system.terminate()
  }

}
