object FoldAndReduce extends App {
  println("#foldLeft means start from left with a seed value")
  val foldLeftResult = (1 to 3).foldLeft(0)((total:Int, next:Int) => total + next) //0 in foldLeft(0) is a seed value that is start value
  println(foldLeftResult) //6

  val foldLeftResult2 = (1 to 3).foldLeft(0){(total:Int, next:Int) =>
    println(s"Total: $total, Next: $next")
    total + next
  }
  println(foldLeftResult2)
//  Total: 0, Next: 1
//  Total: 1, Next: 2
//  Total: 3, Next: 3
//  6

  val foldRightResult = (1 to 3).foldRight(0) { (next: Int, total: Int) =>
    println(s"Total: $total, Next: $next")
    total + next
  }
  println(foldRightResult)
//  Total: 0, Next: 3
//  Total: 3, Next: 2
//  Total: 5, Next: 1
//  6

  println((1 to 3).foldLeft(0)((total, next) => total + next)) //6

  println("#reduceLeft means start from left without a seed value")
  val reduceLeftResult = (1 to 3).reduceLeft{(total:Int, next:Int) =>
    println(s"Total: $total, Next: $next")
    total + next
  }
  println(reduceLeftResult)
//  Total: 1, Next: 2
//  Total: 3, Next: 3
//  6

  val reduceRightResult = (1 to 3).reduceRight{(next:Int, total:Int) =>
    println(s"Total: $total, Next: $next")
    total + next
  }
  println(reduceRightResult)
//  Total: 3, Next: 2
//  Total: 5, Next: 1
//  6

  println((1 to 5).sum) //15
  println((1 to 5).product) //120 = 1x2x3x4x5
}
