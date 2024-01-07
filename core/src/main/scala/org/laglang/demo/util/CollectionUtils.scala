package org.laglang.demo.util

import scala.collection.immutable

object CollectionUtils {

  /**
   * Same function as `keys.zipWithIndex.toMap`, but has perf gain.
   */
  def toMapWithIndex[K](keys: Iterable[K]): Map[K, Int] = {
    val builder = immutable.Map.newBuilder[K, Int]
    val keyIter = keys.iterator
    var idx = 0
    while (keyIter.hasNext) {
      builder += ((keyIter.next(), idx))
      idx = idx + 1
    }
    builder.result()
  }

}
