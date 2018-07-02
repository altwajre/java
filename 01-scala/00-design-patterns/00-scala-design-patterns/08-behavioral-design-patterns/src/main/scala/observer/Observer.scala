package observer

import scala.collection.mutable.ListBuffer

/*
The observer design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/37340e3e-29ef-48cd-ba6a-5890a287046d.xhtml

 */
object Observer {

  // Models
  case class Comment(user: User, text:String)

  case class Post(user: User, text: String) extends Observable[Post] {
    val comments = ListBuffer[Comment]()
    def addComment(comment: Comment): Unit = {
      comments.+=:(comment)
      notifyObservers()
    }
  }

  trait Observer[T] {
    def handleUpdate(subject: T)
  }

  trait Observable[T] {
    this: T =>
    private val observers = ListBuffer[Observer[T]]()

    def addObserver(observer: Observer[T]): Unit = {
      observers.+=:(observer)
    }

    def notifyObservers(): Unit = {
      observers.foreach(_.handleUpdate(this))
    }
  }

  case class User(name: String) extends Observer[Post] {
    override def handleUpdate(subject: Post): Unit = {
      println(s"I'm ${name}. The post got new comments: ${subject.comments}")
    }
  }

  def main(args: Array[String]): Unit = {
    val tom = User("Tom")
    val dick = User("Dick")
    val harry = User("Harry")
    println("Tom create a post")
    val post = Post(tom, "post from Tom")
    println("Tom add a comment")
    post.addComment(Comment(tom, "Hope you like it."))
    println("Dick and Harry subscribe to the post.")
    post.addObserver(dick)
    post.addObserver(harry)
    println("Tom add a comment")
    post.addComment(Comment(tom, "Do you like it?"))
    println("Harry add a comment")
    post.addComment(Comment(harry, "It is amazing!"))
  }
}
/*
Tom create a post
Tom add a comment
Dick and Harry subscribe to the post.
Tom add a comment
I'm Harry. The post got new comments: ListBuffer(Comment(User(Tom),Do you like it?), Comment(User(Tom),Hope you like it.))
I'm Dick. The post got new comments: ListBuffer(Comment(User(Tom),Do you like it?), Comment(User(Tom),Hope you like it.))
Harry add a comment
I'm Harry. The post got new comments: ListBuffer(Comment(User(Harry),It is amazing!), Comment(User(Tom),Do you like it?), Comment(User(Tom),Hope you like it.))
I'm Dick. The post got new comments: ListBuffer(Comment(User(Harry),It is amazing!), Comment(User(Tom),Do you like it?), Comment(User(Tom),Hope you like it.))
 */
