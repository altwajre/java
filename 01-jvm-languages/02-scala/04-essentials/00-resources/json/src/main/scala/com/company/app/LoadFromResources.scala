package com.company.app

import org.json4s._
import org.json4s.jackson.JsonMethods._

case class User(firstName: String, lastName: String, age: Int)

object LoadFromResources {
  def main(args: Array[String]) = {
    implicit val formats = DefaultFormats

    val users: List[User] = parse(StreamInput(getClass.getResourceAsStream("/users.json"))).extract[List[User]]
    println(users)
  }
}
/*
List(User(Tom,Niko,28), User(John,Smith,18), User(Harry,Cooper,38))
 */
