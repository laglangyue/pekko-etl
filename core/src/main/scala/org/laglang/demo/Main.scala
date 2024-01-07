package org.laglang.demo

import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.stream.scaladsl.Keep
import org.laglang.demo.connector.{Reader, Writer}

object Main {

  implicit val system: ActorSystem = ActorSystem("QuickStart")
  // test
  def main(args: Array[String]): Unit = {
    //
    val reader: Reader = null
    val writer: Writer = null

    val task = reader.source.map(r => r.copy()).toMat(writer.sink())(Keep.right)
    val run = task.run _
  }

}
