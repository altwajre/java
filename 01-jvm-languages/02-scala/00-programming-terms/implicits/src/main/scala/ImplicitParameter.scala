object ImplicitParameter extends App {
  def doItFewTimes(numTimes: Int)(implicit hiString: HiString) = {
    for (i <- 1 to numTimes) {
      hiString.sayHi()
    }
  }

  implicit val a = new HiString("Tom")

  doItFewTimes(3)
}

/*
Hi, my name is Tom
Hi, my name is Tom
Hi, my name is Tom
 */
