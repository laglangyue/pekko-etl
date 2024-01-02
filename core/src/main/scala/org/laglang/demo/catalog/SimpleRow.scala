package org.laglang.demo.catalog

/**
 * A row implementation that uses an array of objects as the underlying storage. Note that, while
 * the array is not copied, and thus could technically be mutated after creation, this is not
 * allowed.
 */
class SimpleRow(val values: Array[Any]) extends Row {

  /** No-arg constructor for serialization. */
  protected def this() = this(null)

  def this(size: Int) = this(new Array[Any](size))

  override def length: Int = values.length

  override def get(i: Int): Any = values(i)

  override def toSeq: Seq[Any] = values.clone().toSeq

  override def copy(): SimpleRow = this

}

object SimpleRow {
  def apply(values: Array[Any]): SimpleRow = new SimpleRow(values)

  def apply(any: Any*): SimpleRow = new SimpleRow(any.toArray)
}
