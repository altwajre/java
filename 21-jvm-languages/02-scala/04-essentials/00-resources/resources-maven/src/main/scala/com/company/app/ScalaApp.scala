package com.company.app

import scala.io.Source

object ScalaApp {
  def main(args: Array[String]): Unit = {
    val stream = getClass.getResourceAsStream("/data/user.json")
    val userJson = Source.fromInputStream(stream).mkString
    println(userJson)
  }
  /*
{
  "id": 1,
  "name": {
    "first": "Yong",
    "last": "Tom"
  },
  "contact": [
    {"type": "phone/home", "ref": "111-111-1234"},
    {"type": "phone/work", "ref": "222-222-2222"}
  ]
}
   */
}
