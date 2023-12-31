package org.laglang.demo.catalog

import org.laglang.demo.row.DataType

case class StructField(name: String, dataType: DataType, nullable: Boolean = true)
