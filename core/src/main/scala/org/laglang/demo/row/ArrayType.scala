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

object ArrayType extends AbstractDataType {

  /**
   * Construct a [[ArrayType]] object with the given element type. The `containsNull` is true.
   */
  def apply(elementType: DataType): ArrayType = ArrayType(elementType, containsNull = true)

  override def defaultConcreteType: DataType = ArrayType(NullType, containsNull = true)

  override def acceptsType(other: DataType): Boolean = {
    other.isInstanceOf[ArrayType]
  }

  override def simpleString: String = "array"
}

case class ArrayType(elementType: DataType, containsNull: Boolean) extends DataType {

  /**
   * The default size of a value of the ArrayType is the default size of the element type.
   * We assume that there is only 1 element on average in an array. See SPARK-18853.
   */
  override def defaultSize: Int = 1 * elementType.defaultSize

  override def simpleString: String = s"array<${elementType.simpleString}>"

  override def sql: String = s"ARRAY<${elementType.sql}>"

  override def asNullable: ArrayType =
    ArrayType(elementType.asNullable, containsNull = true)

  /**
   * Returns the same data type but set all nullability fields are true
   * (`StructField.nullable`, `ArrayType.containsNull`, and `MapType.valueContainsNull`).
   *
   * @since 4.0.0
   */
  def toNullable: ArrayType = asNullable

  override def existsRecursively(f: (DataType) => Boolean): Boolean = {
    f(this) || elementType.existsRecursively(f)
  }
}
