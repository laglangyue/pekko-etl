package org.laglang.demo.pipline

import org.laglang.demo.connector.demo.FakeConnector
import org.laglang.demo.connector.reader.Reader

object ReaderConnector {

  def create(): Reader = new FakeConnector()

}
