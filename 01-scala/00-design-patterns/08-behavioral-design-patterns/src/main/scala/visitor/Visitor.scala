package visitor

/*
The visitor design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/3a037c7d-5d33-4607-8586-18d6151329e4.xhtml

The visitor design pattern helps us to add new operations to existing object structures without modifying them.

Visitor will has document after document.accept(visitor)
 */
object Visitor {

  // Models: Visitable
  abstract class Element(val text: String) {
    def accept(visitor: Visitor)
  }

  class Title(text: String) extends Element(text) {
    override def accept(visitor: Visitor): Unit = {
      visitor.visit(this)
    }
  }

  class Text(text: String) extends Element(text) {
    override def accept(visitor: Visitor): Unit = {
      visitor.visit(this)
    }
  }

  class Hyperlink(text: String, val url: String) extends Element(text) {
    override def accept(visitor: Visitor): Unit = {
      visitor.visit(this)
    }
  }

  class Document(parts: List[Element]) {
    def accept(visitor: Visitor): Unit = {
      parts.foreach(p => p.accept(visitor))
    }
  }

  // Visitors
  trait Visitor {
    def visit(title: Title)
    def visit(text: Text)
    def visit(hyperlink: Hyperlink)
  }

  class HtmlExporterVisitor extends Visitor {
    val line = System.getProperty("line.separator")
    val builder = new StringBuilder

    def getHtml(): String = builder.toString

    override def visit(title: Title): Unit = {
      builder.append(s"<h1>${title.text}</h1>").append(line)
    }

    override def visit(text: Text): Unit = {
      builder.append(s"<p>${text.text}</p>").append(line)
    }

    override def visit(hyperlink: Hyperlink): Unit = {
      builder.append(s"""<a href=\"${hyperlink.url}\">${hyperlink.text}</a>""").append(line)
    }
  }

  class PlainTextExporterVisitor extends Visitor {
    val line = System.getProperty("line.separator")
    val builder = new StringBuilder

    def getText(): String = builder.toString

    override def visit(title: Title): Unit = {
      builder.append(title.text).append(line)
    }

    override def visit(text: Text): Unit = {
      builder.append(text.text).append(line)
    }

    override def visit(hyperlink: Hyperlink): Unit = {
      builder.append(s"${hyperlink.text} (${hyperlink.url})").append(line)
    }
  }

  def main(args: Array[String]): Unit = {
    val document = new Document(
      List(
        new Title("The Visitor Pattern Example"),
        new Text("The visitor pattern helps us add extra functionality without changing the classes."),
        new Hyperlink("Go check it online!", "https://www.google.com/"),
        new Text("Thanks!")
      )
    )
    val htmlExporter = new HtmlExporterVisitor
    val plainTextExporter = new PlainTextExporterVisitor

    println(s"Before Export to html: empty")
    println(htmlExporter.getHtml())
    println(s"After Export to html: visitor has html document")
    document.accept(htmlExporter)
    println(htmlExporter.getHtml())

    System.out.println(s"Export to plain:")
    document.accept(plainTextExporter)
    System.out.println(plainTextExporter.getText())
  }
}
/*
Before Export to html: empty

After Export to html: visitor has html document
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
