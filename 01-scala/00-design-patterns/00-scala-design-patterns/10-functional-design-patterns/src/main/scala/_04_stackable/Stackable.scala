package _04_stackable

/*
The stackable traits design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/c01a0983-d3d2-4d08-8707-e80b469fc845.xhtml

It it for decorator design pattern.
It is based on mixin composition.

The magic is abstract override modifier on the methods. It allows to call super on the abstract method of the super class.

- The modifications are applied from right to left in the order
val rightToLeftWriter = new BasicStringWriter with CapitalizingStringWriter with LowercasingStringWriter with UppercasingStringWriter

 */
object Stackable {
  abstract class StringWriter {
    def write(data: String): String
  }

  // basic implementation of StringWriter
  class BasicStringWriter extends StringWriter {
    override def write(data: String): String =
      s"Writing the following data: ${data}"
  }

  // stackable traits
  trait CapitalizingStringWriter extends StringWriter {
    abstract override def write(data: String): String = {
      super.write(data.split("\\s+").map(_.capitalize).mkString(" "))
    }
  }

  trait UppercasingStringWriter extends StringWriter {
    abstract override def write(data: String): String = {
      super.write(data.toUpperCase)
    }
  }

  trait LowercasingStringWriter extends StringWriter {
    abstract override def write(data: String): String = {
      super.write(data.toLowerCase)
    }
  }

  def main(args: Array[String]): Unit = {
    val uppercaseWriter = new BasicStringWriter with UppercasingStringWriter
    println(s"uppercaseWriter: ${uppercaseWriter.write("we like learning scala!")}")

    val capitalizingWriter = new BasicStringWriter with CapitalizingStringWriter with LowercasingStringWriter
    println(s"capitalizingWriter: ${capitalizingWriter.write("we like learning scala!")}")

    val rightToLeftWriter = new BasicStringWriter with CapitalizingStringWriter with LowercasingStringWriter with UppercasingStringWriter
    println(s"rightToLeftWriter: ${rightToLeftWriter.write("we like learning scala!")}")
  }
}
/*
uppercaseWriter: Writing the following data: WE LIKE LEARNING SCALA!
capitalizingWriter: Writing the following data: We Like Learning Scala!
rightToLeftWriter: Writing the following data: We Like Learning Scala!
 */
