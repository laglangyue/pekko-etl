package org.laglang.demo.connector

import scala.concurrent.Future

/**
 * Verify if the context is legal
 */
trait Verify {
  def check: Future[Null]
}
