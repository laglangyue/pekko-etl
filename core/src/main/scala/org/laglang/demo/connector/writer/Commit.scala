package org.laglang.demo.connector.writer

import org.laglang.demo.catalog.Row

enum Commit:
  case Success
  case Fail(row: Row, reason: String)
