package com.company.app

object GatlingRunner {

// does NOT work
  def main(args: Array[String]): Unit = {
    val loadTest = new LoadTest()
    loadTest.setUp();
  }

}
