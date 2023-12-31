package org.laglang.demo.table

import org.laglang.demo.util.CollectionUtils

import scala.collection.Map
import org.laglang.demo.row.{AbstractDataType, DataType}

case class StructType(fields: Array[StructField]) extends DataType with Seq[StructField] {

  /** No-arg constructor for kryo. */
  def this() = this(Array.empty[StructField])

  /** Returns all field names in an array. */
  def fieldNames: Array[String] = fields.map(_.name)

  /**
   * Returns all field names in an array. This is an alias of `fieldNames`.
   *
   * @since 2.4.0
   */
  def names: Array[String] = fieldNames

  private lazy val fieldNamesSet: Set[String] = fieldNames.toSet
  private lazy val nameToField: Map[String, StructField] = fields.map(f => f.name -> f).toMap
  private lazy val nameToIndex: Map[String, Int] = CollectionUtils.toMapWithIndex(fieldNames)

  /**
   * Returns the index of a given field.
   *
   * @throws IllegalArgumentException
   *   if a field with the given name does not exist
   */
  def fieldIndex(name: String): Int = {
    nameToIndex.getOrElse(
      name,
      throw new IllegalArgumentException(
        s"$name does not exist. Available: ${fieldNames.mkString(", ")}"))
  }

  /**
   * Creates a new [[StructType]] by adding a new field.
   * {{{
   * val struct = (new StructType)
   *   .add(StructField("a", IntegerType, true))
   *   .add(StructField("b", LongType, false))
   *   .add(StructField("c", StringType, true))
   * }}}
   */
  def add(field: StructField): StructType = {
    StructType(fields :+ field)
  }

  /**
   * Extracts the [[StructField]] with the given name.
   *
   * @throws IllegalArgumentException
   *   if a field with the given name does not exist
   */
  def get(name: String): StructField = {
    nameToField.getOrElse(
      name,
      throw new IllegalArgumentException(
        s"$name does not exist. Available: ${fieldNames.mkString(", ")}"))
  }

  override def length: Int = fields.length

  override def iterator: Iterator[StructField] = fields.iterator

  /**
   * The default size of a value of the StructType is the total default sizes of all field types.
   */
  override def defaultSize: Int = fields.map(_.dataType.defaultSize).sum

  override private[table] def asNullable: StructType = {
    val newFields = fields.map { case StructField(name, dataType, _) =>
      StructField(name, dataType)
    }

    StructType(newFields)
  }
}

object StructType extends AbstractDataType {

  override private[sql] def defaultConcreteType: DataType = new StructType

  override private[sql] def acceptsType(other: DataType): Boolean = {
    other.isInstanceOf[StructType]
  }

  override private[sql] def simpleString: String = "struct"
}
