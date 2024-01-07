/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.laglang.demo.row

import org.laglang.demo.row.DataType.equalsIgnoreCaseAndNullability

import java.util.Locale

abstract class DataType extends AbstractDataType {

  /**
   * The default size of a value of this data type, used internally for size estimation.
   */
  def defaultSize: Int

  /** Name of the type used in JSON serialization. */
  def typeName: String = {
    this.getClass.getSimpleName
      .stripSuffix("$")
      .stripSuffix("Type")
      .toLowerCase(Locale.ROOT)
  }

  /** Readable string representation for the type. */
  override def simpleString: String = typeName

  def sql: String = simpleString.toUpperCase(Locale.ROOT)

  /**
   * Returns the same data type but set all nullability fields are true
   * (`StructField.nullable`, `ArrayType.containsNull`, and `MapType.valueContainsNull`).
   */
  def asNullable: DataType

  /**
   * Returns true if any `DataType` of this DataType tree satisfies the given function `f`.
   */
  def existsRecursively(f: (DataType) => Boolean): Boolean = f(this)

  override def defaultConcreteType: DataType = this

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
  override def acceptsType(other: DataType): Boolean = equalsIgnoreCaseAndNullability(other, this)
}

object DataType {

  /**
   * Compares two types, ignoring nullability of ArrayType, MapType, StructType, and ignoring case
   * sensitivity of field names in StructType.
   */
  def equalsIgnoreCaseAndNullability(from: DataType, to: DataType): Boolean = {
    (from, to) match {
      case (ArrayType(fromElement, _), ArrayType(toElement, _)) =>
        equalsIgnoreCaseAndNullability(fromElement, toElement)

      case (MapType(fromKey, fromValue, _), MapType(toKey, toValue, _)) =>
        equalsIgnoreCaseAndNullability(fromKey, toKey) &&
          equalsIgnoreCaseAndNullability(fromValue, toValue)

      case (fromDataType, toDataType) => fromDataType == toDataType
    }
  }
}
