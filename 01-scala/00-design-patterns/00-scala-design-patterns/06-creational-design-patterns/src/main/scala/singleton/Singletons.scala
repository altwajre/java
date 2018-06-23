package singleton

import scala.collection.concurrent._

/*
The singleton design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d50886f5-ce89-4b1b-92d1-d08601ce2b87.xhtml

using the object keyword in scala for singleton
 */

// static method in singleton
object StringUtils {
  def countNumberOfSpaces(text: String): Int = {
    text.split("\\s+").length - 1
  }
}

object StringUtilsApp {
  def main(args: Array[String]) = {
    val sentence = "Hello there! I am a singleton example."
    println(s"spaces in '$sentence' is: ${StringUtils.countNumberOfSpaces(sentence)}")
  }
}
/*
spaces in 'Hello there! I am a singleton example.' is: 6
 */

// state singleton
object AppRegistry {
  println("Registry initialization block called.")
  private val users: Map[String, String] = TrieMap.empty

  def addUser(id: String, name: String) = {
    users.put(id, name)
  }

  def removeUser(id: String) = {
    users.remove(id)
  }

  def isUserRegistered(id: String): Boolean = {
    users.contains(id)
  }

  def getAllUserNames(): List[String] = {
    users.map(_._2).toList
  }
}

object AppRegistryApp {
  def main(args: Array[String]) = {
    AppRegistry.addUser("1", "Tom")
    AppRegistry.addUser("2", "Dick")
    AppRegistry.addUser("3", "Harry")
    println(s"Is user with ID=1 registered? ${AppRegistry.isUserRegistered("1")}")
    println("Removing ID=2")
    AppRegistry.removeUser("2")
    println(s"Is user with ID=2 registered? ${AppRegistry.isUserRegistered("2")}")
    println(s"All users registered are: ${AppRegistry.getAllUserNames().mkString(",")}")
  }
}
/*
Registry initialization block called.
Is user with ID=1 registered? true
Removing ID=2
Is user with ID=2 registered? false
All users registered are: Harry,Tom
 */
