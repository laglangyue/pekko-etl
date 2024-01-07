package org.laglang.demo

import org.apache.commons.lang3.RandomStringUtils
import org.apache.pekko.actor.typed.ActorSystem
import org.apache.pekko.actor.typed.javadsl.Behaviors
import org.apache.pekko.stream.scaladsl.{Flow, Sink, Source}
import org.slf4j.LoggerFactory

import scala.concurrent.duration.{DurationInt, FiniteDuration}
import scala.concurrent.{Await, ExecutionContext}

object ConsoleToConsole {
  private def wait(duration: FiniteDuration): Unit = Thread.sleep(duration.toMillis)

  private final val log = LoggerFactory.getLogger(getClass)

  /**
   * define a source by iterator
   *
   * @return
   */
  private def reader() = {
    val function = Iterator.iterate(0, Int.MaxValue)(_ + 1)
    Source.fromIterator(() => function)
  }

  private def flow() = {
    Flow[Int]
      .map(index => s"${index} ${RandomStringUtils.randomAscii(index % 10)}")
      .async
  }

  private def writer() = {
    Sink.foreach(println)
  }

  def main(args: Array[String]): Unit = {
    implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "console-to-console")
    implicit val executionContext: ExecutionContext = actorSystem.executionContext
    val task = reader()
      .via(flow())
      .runWith(writer())
    task.failed.foreach(exception => log.error("failure", exception))
    task.onComplete(_ => println("clean"))
    wait(10.seconds)
    actorSystem.terminate()
    Await.result(actorSystem.whenTerminated, 1.seconds)
  }

}
