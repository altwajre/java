package com.company.app._03basics

import java.time.LocalDate

import org.scalatest.FunSuite

class _08Strings extends FunSuite{
  test("smart strings") {
    val message = "Lunch at 12:30PM"
    println(message.getClass().getName)
    var regex = """(\s|[0-9])?[0-9]:[0-5][0-9]\s*(AM|PM)""".r // .r means it is a regex
    println(regex.findAllIn(message).toList)
  }
  /*
  List(12:30PM)
   */

  test("string formatting") {
    val javaFormat = String.format("%s style", "Java")
    println(javaFormat)
    // Java style

    val scalaFormat = "%s style".format("Scala")
    println(scalaFormat)
    // Scala style

    println("ascending: %s, %s, %s".format(1, 2, 3))
    // ascending: 1, 2, 3

    // Position arguments
    println("descending: %3$s, %2$s, %1$s".format(1, 2, 3))
    // descending: 3, 2, 1

    // Use the first argument over and over to separate the month, date, and year
    println("My birthday on %1$tB the %1$te in the year %1$tY".format(LocalDate.now))
    // My birthday on April the 8 in the year 2018

    val lyrics =
      """I see trees of %s
        |%s roses too
        |I see them bloom
        |For me and you
        |And I think to myself
        |What a wonderful world
      """.stripMargin.format("green", "Red")
    println(lyrics)
    /*
I see trees of green
Red roses too
I see them bloom
For me and you
And I think to myself
What a wonderful world
     */

  }

  test("string interpolation") {
    val a = 8
    println(s"${a} dollars") // s"" means using string interpolation
    // 8 dollars

    println(s"${a + 1} dollars")
    // 9 dollars

    // f interpolation
    val ticketCost = 50
    val bandName = "Psychedelic Furs"
    val percenIncrease = 20
    val musicGenre = "New Wave"
    println(f"$bandName%s tickets are $$$ticketCost%1.2f%n$percenIncrease%% bump because everyone likes $musicGenre")
    /*
Psychedelic Furs tickets are $50.00
20% bump because everyone likes New Wave
     */

    // smart string
    println(
      f"""$bandName%s tickets are $$$ticketCost%1.2f
         |$percenIncrease%% bump because everyone likes $musicGenre""".stripMargin)
    /*
Psychedelic Furs tickets are $50.00
20% bump because everyone likes New Wave
     */
  }

}
