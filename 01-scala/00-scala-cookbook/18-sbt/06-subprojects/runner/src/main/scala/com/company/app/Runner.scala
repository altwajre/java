package com.company.app

import com.company.common.Customer

object Runner {
  def main(args: Array[String]): Unit = {
    val tom = Customer("Tom")
    println(tom)
  }
}
