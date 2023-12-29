package org.laglang.demo.row

class ByteType private() extends IntegralType {
  /**
   * The default size of a value of the ByteType is 1 byte.
   */
  override def defaultSize: Int = 1

  override def simpleString: String = "tinyint"

  override def asNullable: ByteType = this
}

case object ByteType extends ByteType

class ShortType private () extends IntegralType {

  override def defaultSize: Int = 2

  override def simpleString: String = "smallint"

  override def asNullable: ShortType = this
}

case object ShortType extends ShortType

class IntType private() extends IntegralType {
  /**
   * The default size of Int is 4 bytes.
   */
  override def defaultSize: Int = 4

  override def simpleString: String = "int"

  override def asNullable: IntType = this
}

case object IntType extends IntType


class LongType private() extends IntegralType {
  /**
   * The default size of Long is 8 bytes.
   */
  override def defaultSize: Int = 8

  override def simpleString: String = "bigint"

  override def asNullable: LongType = this
}

case object LongType extends LongType


class FloatType extends FractionalType {

  /**
   * The default size of a value of the FloatType is 4 bytes.
   */
  override def defaultSize: Int = 4

  override def asNullable: FloatType = this
}

case object FloatType extends FloatType


class DoubleType private() extends FractionalType {
  /**
   * The default size of a value of the DoubleType is 8 bytes.
   */
  override def defaultSize: Int = 8

  override def asNullable: DoubleType = this
}

case object DoubleType extends DoubleType

