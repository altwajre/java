package com.company.app

import com.fasterxml.jackson.databind.ObjectMapper

import scala.io.Source

object LoadJson {
  val mapper = new ObjectMapper

  def main(args: Array[String]): Unit = {

    stringToJson
//    streamToJson
//    loadJsonToString
  }

  private def stringToJson = {
    val source = Source.fromResource("data/user.json")
    val jsonNode = mapper.readTree(source.mkString)
    println(jsonNode)
  }
  /*
  {"id":1,"name":{"first":"Yong","last":"Tom"},"contact":[{"type":"phone/home","ref":"111-111-1234"},{"type":"phone/work","ref":"222-222-2222"}]}
   */

  private def streamToJson = {
    val stream = getClass.getResourceAsStream("/data/user.json")
    val jsonNode = mapper.readTree(stream)
    println(jsonNode)
  }
  /*
  {"id":1,"name":{"first":"Yong","last":"Tom"},"contact":[{"type":"phone/home","ref":"111-111-1234"},{"type":"phone/work","ref":"222-222-2222"}]}
   */

  private def loadJsonToString = {
    val stream = getClass.getResourceAsStream("/data/user.json")
    val userString = Source.fromInputStream(stream).mkString
    println(userString)
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
