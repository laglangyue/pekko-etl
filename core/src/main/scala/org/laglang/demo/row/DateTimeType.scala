package org.laglang.demo.row

class DateType  extends DatetimeType {

  /**
   * The default size of a value of the DateType is 4 bytes.
   */
  override def defaultSize: Int = 4

  override def asNullable: DateType = this

}

case object DateType extends DateType

class TimeType extends DatetimeType {

  override def defaultSize: Int = 4

  override def asNullable: TimeType = this
}

case object TimeType extends TimeType

class TimestampType private () extends DatetimeType {

  override def defaultSize: Int = 8

  override def asNullable: TimestampType = this
}

case object TimestampType extends TimestampType

class TimestampNTZType private () extends DatetimeType {

  override def defaultSize: Int = 8

  override def typeName: String = "timestamp_ntz"

  override def asNullable: TimestampNTZType = this
}

case object TimestampNTZType extends TimestampNTZType
