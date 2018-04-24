package com.company.app

import play.api.libs.json._

object ScalaApp {
  def main(args: Array[String]): Unit = {

    // create json from string
    val json: JsValue = Json.parse("""
  {
    "name" : "Watership Down",
    "location" : {
      "lat" : 51.235685,
      "long" : -1.309197
    },
    "residents" : [ {
      "name" : "Fiver",
      "age" : 4,
      "role" : null
    }, {
      "name" : "Bigwig",
      "age" : 6,
      "role" : "Owsla"
    } ]
  }
  """)


    // get json property value
    val lat = (json \ "location" \ "lat").get
    println(lat)

    // add new json-node
    val updatedJson = json.as[JsObject] + ("age" -> Json.toJson("28"))
    println(updatedJson)

  }

}
