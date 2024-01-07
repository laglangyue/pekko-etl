package org.laglang.demo.pipline

class Pipeline {

  def run() = {
    val reader = ReaderConnector.create()
    val writer = WriterConnector.create()
    reader.source.to(writer.queue())
    reader.source.to(writer.sink())
  }
}
