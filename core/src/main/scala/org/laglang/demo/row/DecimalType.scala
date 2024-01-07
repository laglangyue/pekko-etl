package org.laglang.demo.row

import java.util.Locale
import scala.annotation.tailrec

/**
 * The data type representing `java.math.BigDecimal` values.
 * A Decimal that must have fixed precision (the maximum number of digits) and scale (the number
 * of digits on right side of dot).
 *
 * The precision can be up to 38, scale can also be up to 38 (less or equal to precision).
 *
 * The default precision and scale is (10, 0).
 */
case class DecimalType(precision: Int, scale: Int) extends AtomicType {
  private val MAX_LONG_DIGITS = 18
  // default constructor for Java
  def this(precision: Int) = this(precision, 0)
  def this() = this(10)

  override def typeName: String = s"decimal($precision,$scale)"

  override def toString: String = s"DecimalType($precision,$scale)"

  override def sql: String = typeName.toUpperCase(Locale.ROOT)

  /**
   * Returns whether this DecimalType is wider than `other`. If yes, it means `other`
   * can be casted into `this` safely without losing any precision or range.
   */
  private def isWiderThan(other: DataType): Boolean = isWiderThanInternal(other)

  @tailrec
  private def isWiderThanInternal(other: DataType): Boolean = other match {
    case dt: DecimalType =>
      (precision - scale) >= (dt.precision - dt.scale) && scale >= dt.scale
    case dt: IntegralType =>
      isWiderThanInternal(DecimalType.forType(dt))
    case _ => false
  }

  /**
   * Returns whether this DecimalType is tighter than `other`. If yes, it means `this`
   * can be casted into `other` safely without losing any precision or range.
   */
  private def isTighterThan(other: DataType): Boolean = other match {
    case dt: DecimalType =>
      (precision - scale) <= (dt.precision - dt.scale) && scale <= dt.scale
    case dt: IntegralType =>
      val integerAsDecimal = DecimalType.forType(dt)
      assert(integerAsDecimal.scale == 0)
      // If the precision equals `integerAsDecimal.precision`, there can be integer overflow
      // during casting.
      precision < integerAsDecimal.precision && scale == 0
    case _ => false
  }

  /**
   * The default size of a value of the DecimalType is 8 bytes when precision is at most 18,
   * and 16 bytes otherwise.
   */
  override def defaultSize: Int = if (precision <= MAX_LONG_DIGITS) 8 else 16

  override def simpleString: String = s"decimal($precision,$scale)"

  override def asNullable: DecimalType = this
}

object DecimalType extends AbstractDataType {
  import scala.math.min

  private val MAX_PRECISION = 38
  private val MAX_SCALE = 38
  private val DEFAULT_SCALE = 18
  private val SYSTEM_DEFAULT: DecimalType = DecimalType(MAX_PRECISION, DEFAULT_SCALE)

  // The decimal types compatible with other numeric types
  private val BooleanDecimal = DecimalType(1, 0)
  private val ByteDecimal = DecimalType(3, 0)
  private val ShortDecimal = DecimalType(5, 0)
  private val IntDecimal = DecimalType(10, 0)
  private val LongDecimal = DecimalType(20, 0)
  private val FloatDecimal = DecimalType(14, 7)
  private val DoubleDecimal = DecimalType(30, 15)
  private val BigIntDecimal = DecimalType(38, 0)

  private def forType(dataType: DataType): DecimalType = dataType match {
    case ByteType   => ByteDecimal
    case ShortType  => ShortDecimal
    case IntType    => IntDecimal
    case LongType   => LongDecimal
    case FloatType  => FloatDecimal
    case DoubleType => DoubleDecimal
  }

  override  def defaultConcreteType: DataType = SYSTEM_DEFAULT

  override  def acceptsType(other: DataType): Boolean = {
    other.isInstanceOf[DecimalType]
  }

  override  def simpleString: String = "decimal"

  private object Fixed {
    def unapply(t: DecimalType): Option[(Int, Int)] = Some((t.precision, t.scale))
  }

  def unapply(t: DataType): Boolean = t.isInstanceOf[DecimalType]
}
