/*
Self type

https://docs.scala-lang.org/tour/self-types.html

Self-types are a way to declare that a trait must be mixed into another trait, even though it doesn't directly extend it.
That makes the members of the dependency available without imports.
 */

trait User {
  def username: String
}

object SelfType {
  def main(args: Array[String]) = {

    trait Tweeter {
      this: User => // self-type: reassign this
      def tweet(tweetText: String) = println(s"$username: $tweetText")
    }

    class VerifiedTweeter(val username_ : String) extends Tweeter with User {
      override def username: String = s"real $username_"
    }

    val realTom = new VerifiedTweeter("Tom")
    realTom.tweet("Just have dinner")
  }
}
/*
real Tom: Just have dinner
 */
