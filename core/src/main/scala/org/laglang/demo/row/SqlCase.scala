package org.laglang.demo.row


case class CharType(length: Int) extends AtomicType {

  require(length >= 0, "The length of char type cannot be negative.")

  override def defaultSize: Int = length
  override def typeName: String = s"char($length)"
  override def toString: String = s"CharType($length)"
  override def asNullable: CharType = this
}

case class VarcharType(length: Int) extends AtomicType {
  require(length >= 0, "The length of varchar type cannot be negative.")

  override def defaultSize: Int = length
  override def typeName: String = s"varchar($length)"
  override def toString: String = s"VarcharType($length)"
  override def asNullable: VarcharType = this
}
