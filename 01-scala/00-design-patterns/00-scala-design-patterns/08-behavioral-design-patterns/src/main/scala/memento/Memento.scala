package memento

import scala.collection.mutable

/*
The memento design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/b98ebd14-53a7-4b28-b2e3-b6083c60298c.xhtml

The purpose of the memento design pattern is to provide the ability to execute an undo action in order to restore
an object to a previous state.

- Originator: the object whose state we want to be able to restore
- Caretaker: The object that triggers the changes to the originator object and uses the memento objects for rollback
- Memento: The object that carries the actual state of the originator and can be used to restore to one of the previous states

The memento object can be handled only by the originator. The caretaker and all other classes can just store it and nothing else.
 */
object Memento {
  trait Memento[T] {
    protected val state: T
    def getState(): T = state
  }

  trait Caretaker[T] {
    val states: mutable.Stack[Memento[T]] = mutable.Stack[Memento[T]]()
  }

  trait Originator[T] {
    def createMemnto: Memento[T]
    def restore(memento: Memento[T])
  }

  class TextEditor extends Originator[String] {
    private var builder: StringBuilder = new StringBuilder
    def append(text: String): Unit = {
      builder.append(text)
    }
    def deleteOneChar(): Unit = {
      if(builder.nonEmpty) {
        builder.deleteCharAt(builder.length - 1)
      }
    }
    override def createMemnto: Memento[String] = new TextEditorMemento(builder.toString)
    override def restore(memento: Memento[String]): Unit = {
      this.builder = new StringBuilder(memento.getState())
    }
    def text(): String = builder.toString
    private class TextEditorMemento(val state: String) extends Memento[String]
  }

  class TextEditorManipulator extends Caretaker[String] {
    private val textEditor = new TextEditor
    def save(): Unit = {
      states.push(textEditor.createMemnto)
//      println(states)
    }
    def undo(): Unit = {
      if(states.nonEmpty) {
        textEditor.restore(states.pop())
      }
    }
    def append(text: String): Unit = {
      save()
      textEditor.append(text)
    }
    def deleteOneChar(): Unit = {
      save()
      textEditor.deleteOneChar()
    }
    def readText(): String = textEditor.text()
  }

  def main(args: Array[String]) = {
    val textEditorManipulator = new TextEditorManipulator
    textEditorManipulator.append("This is a chapter about memento.")
    println(s"The text is: '${textEditorManipulator.readText()}'")
    // delete 2 characters
    println("Deleting 2 characters...")
    textEditorManipulator.deleteOneChar()
    textEditorManipulator.deleteOneChar()
    // see the text
    println(s"The text is: '${textEditorManipulator.readText()}'")
    // undo
    println("Undoing...")
    textEditorManipulator.undo()
    println(s"The text is: '${textEditorManipulator.readText()}'")
    // undo again
    println("Undoing...")
    textEditorManipulator.undo()
    println(s"The text is: '${textEditorManipulator.readText()}'")
  }
}
/*
The text is: 'This is a chapter about memento.'
Deleting 2 characters...
The text is: 'This is a chapter about mement'
Undoing...
The text is: 'This is a chapter about memento'
Undoing...
The text is: 'This is a chapter about memento.'
 */
