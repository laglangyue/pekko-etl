package org.laglang.demo.newrow

trait Type {
  def size: Int
}

enum DataType(val length: Int) extends Type:

  case Bool extends DataType(1)
  case Byte extends DataType(8)
  case Short extends DataType(16)
  case Int extends DataType(32)
  case Long extends DataType(64)
  case Float extends DataType(32)
  case Double extends DataType(64)
  case Date extends DataType(16)
  case Time extends DataType(16)
  case DateTime extends DataType(32)
  case TimeStamp extends DataType(64)

  override def size = length

enum Complex extends Type:
  override def size: Int = Int.MaxValue

  case ArrayType(element: DataType, length: Int) extends Complex
  case Map(element: DataType)
  case Row(columns: Type*)


