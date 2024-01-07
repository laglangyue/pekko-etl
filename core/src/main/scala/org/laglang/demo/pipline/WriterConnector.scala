package org.laglang.demo.pipline

import org.laglang.demo.connector.internal.ConsoleConnector

object WriterConnector {

  def create() = new ConsoleConnector

}
