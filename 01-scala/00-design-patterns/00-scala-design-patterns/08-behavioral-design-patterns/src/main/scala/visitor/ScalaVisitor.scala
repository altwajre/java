package visitor

/*
The visitor design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/e3563db5-2ecd-4b4c-b50a-a175b0d028f9.xhtml

Pass functions to the accept method.

Visitor will has document after document.accept(visitor)
 */
object ScalaVisitor {
  abstract class Element(text: String) {
    def accept(visitor: Element => Unit): Unit = {
      visitor(this)
    }
  }

  case class Title(text: String) extends Element(text)
  case class Text(text: String) extends Element(text)
  case class Hyperlink(text: String, val url: String) extends Element(text)

  class Document(parts: List[Element]) {

    def accept(visitor: Element => Unit): Unit = {
      parts.foreach(p => p.accept(visitor))
    }
  }

  val line = System.getProperty("line.separator")

  def htmlExporterVisitor(builder: StringBuilder): Element => Unit = {
    case Title(text) =>
      builder.append(s"<h1>${text}</h1>").append(line)
    case Text(text) =>
      builder.append(s"<p>${text}</p>").append(line)
    case Hyperlink(text, url) =>
      builder.append(s"""<a href=\"${url}\">${text}</a>""").append(line)
  }

  def plainTextExporterVisitor(builder: StringBuilder): Element => Unit = {
    case Title(text) =>
      builder.append(text).append(line)
    case Text(text) =>
      builder.append(text).append(line)
    case Hyperlink(text, url) =>
      builder.append(s"${text} (${url})").append(line)
  }

  def main(args: Array[String]): Unit = {
    val document = new Document(
      List(
        Title("The Visitor Pattern Example"),
        Text("The visitor pattern helps us add extra functionality without changing the classes."),
        Hyperlink("Go check it online!", "https://www.google.com/"),
        Text("Thanks!")
      )
    )

    val html = new StringBuilder
    println(s"Export to html:")
    document.accept(htmlExporterVisitor(html))
    println(html.toString())

    val plain = new StringBuilder
    println(s"Export to plain:")
    document.accept(plainTextExporterVisitor(plain))
    println(plain.toString())
  }
}
/*
Export to html:
<h1>The Visitor Pattern Example</h1>
<p>The visitor pattern helps us add extra functionality without changing the classes.</p>
<a href="https://www.google.com/">Go check it online!</a>
<p>Thanks!</p>

Export to plain:
The Visitor Pattern Example
The visitor pattern helps us add extra functionality without changing the classes.
Go check it online! (https://www.google.com/)
Thanks!
 */
