package _00_example

import java.time.LocalDateTime

object IteratorApp {
  def main(args: Array[String]): Unit = {
    val now = LocalDateTime.now()
    println(now)
    val firstRun = now.withHour(13).withMinute(3)
    println(firstRun)
    val isBefore = firstRun.isBefore(now)
    println(isBefore)

    var tmp = firstRun
    println(tmp)
    val list = Iterator.continually({
      tmp = tmp.plusHours(1);
      println(s"tmp ${tmp.getHour}");
      tmp
    })
      .takeWhile(d => {
        println(s"d: ${d.getHour}")
        val bool = d.isBefore(now)
        println(bool)
        bool
      })
      .toList
    println(list)
    val time = list
      .lastOption
      //      .getOrElse(
      //        {
      //          println(isBefore)
      //          if (isBefore) {
      //            println(s"firstRun: $firstRun")
      //            firstRun
      //          }
      //          else
      //            println("else")
      //          firstRun.minusHours(1)
      //        }
      //      )
      .get
      .plusHours(1)
    println(time)
    //    println(time.lastOption.get.getHour)

  }
}
/*
2018-07-16T17:20:10.512
2018-07-16T13:03:10.512
true
2018-07-16T13:03:10.512
tmp 14
d: 14
true
tmp 15
d: 15
true
tmp 16
d: 16
true
tmp 17
d: 17
true
tmp 18
d: 18
false
List(2018-07-16T14:03:10.512, 2018-07-16T15:03:10.512, 2018-07-16T16:03:10.512, 2018-07-16T17:03:10.512)
2018-07-16T18:03:10.512
 */
