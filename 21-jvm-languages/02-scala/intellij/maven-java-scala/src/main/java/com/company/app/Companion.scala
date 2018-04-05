package com.company.app

class Companion {
  def hello(): Unit = {
    println("Hello (class)")
  }
}

object Companion {
  def hallo(): Unit = {
    println("Hello (object)")
  }
}
