import akka.actor.ActorSystem

// https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/fa5dbaab-ff03-4638-8944-2d8c226a29b2.xhtml
object CreateAkkaSystemApp extends App {
  private val system = ActorSystem("HelloAkka")
  println(system)
  system.terminate()
}
/*
akka://HelloAkka
 */
