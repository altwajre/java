
/*
Mixing in traits with variables

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2dac4b60-f2bd-4c69-a0da-5683b12be5fd.xhtml

For completeness, case classes automatically have the val keyword prepended to parameters.
After that we said it means that when using val, we actually have a field with the given name and the right scope,
and it will automatically override whatever the trait requires us to do.
 */

// pass "trait Notifier.notificationMessage" as a variable to the constructor
case class MixingInTraitsWithVariables(val notificationMessage: String) extends Notifier {
  override def clear() = println("cleared")
}

object MixingInTraitsWithVariablesApp extends App {
  private val mixingInTraitsWithVariables = MixingInTraitsWithVariables("hello")
  println(mixingInTraitsWithVariables.notificationMessage)
  mixingInTraitsWithVariables.clear
}
/*
hello
cleared
 */
