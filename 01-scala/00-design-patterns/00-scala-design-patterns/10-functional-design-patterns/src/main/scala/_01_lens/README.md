# The lens design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1133461a-f715-4cbb-b248-be8b35c895c7.xhtml

Lenses: A Functional Imperative

getter and setter

https://www.youtube.com/watch?v=efv0SQNde5Q

Lenses are composable functional, field accessors

case class Lens[A, B] {
  g: A => B,      // getter: from A get B
  s: (B, A) => A  // setter: set B value inside of A; given a B, and replace with A 
}

They create actual lenses so that for an object of the A type, the calls get and set a value of the B type.
