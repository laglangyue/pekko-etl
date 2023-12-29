package org.laglang.demo.row

class NullType private () extends DataType {
  // The companion object and this class is separated so the companion object also subclasses
  // this type. Otherwise, the companion object would be of type "NullType$" in byte code.
  // Defined with a private constructor so the companion object is the only possible instantiation.
  override def defaultSize: Int = 1

  override def asNullable: NullType = this

  override def typeName: String = "void"
}

case object NullType extends NullType

class BooleanType private () extends AtomicType {
  override def defaultSize: Int = 1
  override def asNullable: BooleanType = this
}

case object BooleanType extends BooleanType

class StringType private () extends AtomicType {
  override def defaultSize: Int = 20
  override def asNullable: StringType = this
}

case object StringType extends StringType


/**
 * The data type representing `Array[Byte]` values.
 * Please use the singleton `DataTypes.BinaryType`.
 */
class BinaryType private () extends AtomicType {

  override def defaultSize: Int = 100

  override def asNullable: BinaryType = this
}

case object BinaryType extends BinaryType


