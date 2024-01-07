package org.laglang.demo.row

/**
 * A non-concrete data type, reserved for internal uses.
 */
abstract class AbstractDataType {

  /**
   * The default concrete type to use if we want to cast a null literal into this type.
   */
  def defaultConcreteType: DataType

  /**
   * Returns true if `other` is an acceptable input type for a function that expects this,
   * possibly abstract DataType.
   *
   * {{{
   *   // this should return true
   *   DecimalType.acceptsType(DecimalType(10, 2))
   *
   *   // this should return true as well
   *   NumericType.acceptsType(DecimalType(10, 2))
   * }}}
   */
  def acceptsType(other: DataType): Boolean

  /** Readable string representation for the type. */
  def simpleString: String
}

/**
 * An internal type used to represent everything that is not null, UDTs, arrays, structs, and
 * maps.
 */
protected abstract class AtomicType extends DataType

abstract class NumericType extends AtomicType

abstract class DatetimeType extends AtomicType

abstract class IntegralType extends NumericType

abstract class FractionalType extends NumericType
