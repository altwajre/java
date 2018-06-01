
trait IOrder {
  def create(requestBody: String)

  def get(id: Int)
}

class ClassOrder extends IOrder {
  override def create(requestBody: String): Unit = {
    println("ClassOrder: " + requestBody)
  }

  override def get(id: Int): Unit = {
    println("ClassOrder: " + id)
  }
}

case object CaseObjectOrder extends IOrder {
  override def create(requestBody: String): Unit = {
    println("CaseObjectOrder " + requestBody)
  }

  override def get(id: Int): Unit = {
    println("CaseObjectOrder " + id)
  }
}

object Runner {
  def main(args: Array[String]) = {
    println("# ClassOrder")
    val classOrder = new ClassOrder()
    classOrder.create("my request-body")
    classOrder.get(18)

    println("# CaseObjectOrder")
    CaseObjectOrder.create("your request-body")
    CaseObjectOrder.get(28)

  }
}

/*
# ClassOrder
ClassOrder: my request-body
ClassOrder: 18
# CaseObjectOrder
CaseObjectOrder your request-body
CaseObjectOrder 28
 */
