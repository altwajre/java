package state

/*
The state design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/85c99b36-1bd4-400e-9e63-20d36177feb6.xhtml

- The strategy design pattern is about how and action is performed. It is usually an algorithm that produces the same results as other algorithms.
- The state design pattern is about what action is performed. Depending on the state, an object could be doing different things.
 */
object State {
  // Model
  case class MediaPlayer() {
    private var state: State[MediaPlayer] = new Paused
    def pressPlayOrPauseButton(): Unit = {
      state.press(this)
    }
    def setState(state: State[MediaPlayer]): Unit = {
      this.state = state
    }
  }

  trait State[T] {
    def press(context: T)
  }

  class Playing extends State[MediaPlayer] {
    override def press(context: MediaPlayer): Unit = {
      println("Pressing pause")
      context.setState(new Paused)
    }
  }

  class Paused extends State[MediaPlayer] {
    override def press(context: MediaPlayer): Unit = {
      println("Pressing play")
      context.setState(new Playing)
    }
  }

  def main(args: Array[String]) = {
    val player = MediaPlayer()
    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
    player.pressPlayOrPauseButton()
  }
}
/*
Pressing play
Pressing pause
Pressing play
Pressing pause
 */
