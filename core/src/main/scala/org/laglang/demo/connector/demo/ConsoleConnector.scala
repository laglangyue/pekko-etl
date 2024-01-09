package org.laglang.demo.connector.demo;

import org.apache.pekko.NotUsed
import org.apache.pekko.stream.scaladsl.Flow
import org.laglang.demo.catalog.Row
import org.laglang.demo.connector.writer.{Commit, Writer}


class ConsoleConnector extends Writer {

  override def sink(): Flow[Row, Commit, NotUsed] = {
    Flow.fromFunction(
      row =>
        println(row.toString)
        Commit.Success
    )
  }
}

object ConsoleConnector
