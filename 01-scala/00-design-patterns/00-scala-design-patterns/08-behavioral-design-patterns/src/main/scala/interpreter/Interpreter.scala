package interpreter

import java.util.StringTokenizer

import scala.collection.JavaConverters._
import scala.collection.mutable

/*
The interpreter design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/4cac6b86-b536-40d5-8c8c-3feae75b2b71.xhtml
 */
object Interpreter {

  trait Expression {
    def interpret(): Int
  }

  class Add(right: Expression, left: Expression) extends Expression {
    override def interpret(): Int = left.interpret() + right.interpret()
  }

  class Subtract(right: Expression, left: Expression) extends Expression {
    override def interpret(): Int = left.interpret() - right.interpret()
  }

  class Multiply(right: Expression, left: Expression) extends Expression {
    override def interpret(): Int = left.interpret() * right.interpret()
  }

  class Number(n: Int) extends Expression {
    override def interpret(): Int = n
  }

  object Expression {
    def apply(operator: String, left: => Expression, right: => Expression): Option[Expression] =
      operator match {
        case "+" => Some(new Add(right, left))
        case "-" => Some(new Subtract(right, left))
        case "*" => Some(new Multiply(right, left))
        case i if i.matches("\\d+") => Some(new Number(i.toInt))
        case _ => None
      }
  }

  class RPNParser {

    def parse(expression: String): Expression = {
      val tokenizer = new StringTokenizer(expression)
      tokenizer.asScala.foldLeft(mutable.Stack[Expression]()) {
        case (result, token) =>
          val item = Expression(token.toString, result.pop(), result.pop())
          item.foreach(result.push)
          result
      }.pop()
    }
  }

  class RPNInterpreter {
    def interpret(expression: Expression): Int = expression.interpret()
  }

  def main(args: Array[String]) = {
    val expr1 = "1 2 + 3 * 9 10 + -" // (1 + 2) * 3 - (9 + 10) = -10
    val expr2 = "1 2 3 4 5 * * - +" // 1 + 2 - 3 * 4 * 5 = -57
    val expr3 = "12 -" // invalid
    val parser = new RPNParser
    val interpreter = new RPNInterpreter

    println(s"The result of '${expr1}' is: ${interpreter.interpret(parser.parse(expr1))}")
    println(s"The result of '${expr2}' is: ${interpreter.interpret(parser.parse(expr2))}")
    try {
      println(s"The result is: ${interpreter.interpret(parser.parse(expr3))}")
    } catch {
      case _: Throwable => System.out.println(s"'$expr3' is invalid.")
    }
  }
}
/*
The result of '1 2 + 3 * 9 10 + -' is: -10
The result of '1 2 3 4 5 * * - +' is: -57
'12 -' is invalid.
 */
