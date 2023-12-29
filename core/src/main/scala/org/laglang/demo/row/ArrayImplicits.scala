package org.laglang.demo.row
import scala.collection.immutable

private[spark] object ArrayImplicits {

  implicit class SparkArrayOps[T](xs: Array[T]) {

    /**
     * Wraps an Array[T] as an immutable.ArraySeq[T] without copying.
     */
    def toImmutableArraySeq: immutable.ArraySeq[T] =
      immutable.ArraySeq.unsafeWrapArray(xs)
  }
}

