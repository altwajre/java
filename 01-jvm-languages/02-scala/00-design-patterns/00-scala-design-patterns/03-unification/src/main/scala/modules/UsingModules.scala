package modules

/*
Modules and objects

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1ae9fac2-e3f8-448d-93c5-cb0f1dc932e2.xhtml

Modules are a way to organize programs.
They are interchangeable and plugable pieces of code that have well-defined interfaces and hidden implementations.
Modules are objects
 */
// interface of modules
trait Tick {
  trait Ticker {
    def count(): Int
    def tick(): Unit
  }
  def ticker: Ticker
}

// module implements Tick interface
trait TickUser extends Tick {
  class TickUserImpl extends Ticker {
    var curr = 0

    override def count(): Int = curr

    override def tick(): Unit = {
      curr = curr + 1
    }
  }

  // singleton object will carry the implementation. the name `ticker` is same as Tick.ticker.
  object ticker extends TickUserImpl
}

// interface
trait Alarm {
  trait Alarmer {
    def trigger(): Unit
  }
  def alarm: Alarmer
}

// module extends both Alarm and Tick modules
trait AlarmUser extends Alarm with Tick {
  class AlarmUserImpl extends Alarmer {
    override def trigger(): Unit = {
      if(ticker.count() % 10 == 0) {
        println(s"Alarm triggered at ${ticker.count()}")
      }
    }
  }

  object alarm extends AlarmUserImpl
}

// using implemented modules in an object. this provides a possibility to plug in a different functionality.
object UsingModules extends AlarmUser with TickUser {
  def main(args: Array[String]) = {
    println("Running the ticker. Should trigger the alarm every 10 times")
    (1 to 30).foreach {
      case i => {
        ticker.tick()
        alarm.trigger()
      }
    }
  }

}
/*
Running the ticker. Should trigger the alarm every 10 times
Alarm triggered at 10
Alarm triggered at 20
Alarm triggered at 30
 */
