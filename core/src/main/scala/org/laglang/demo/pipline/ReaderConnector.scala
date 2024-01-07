package org.laglang.demo.pipline

import org.laglang.demo.connector.Reader
import org.laglang.demo.connector.internal.FakeConnector

object ReaderConnector {

  def create(): Reader = new FakeConnector()

}
