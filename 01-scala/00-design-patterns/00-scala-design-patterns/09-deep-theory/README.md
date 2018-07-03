# Functional Design Patterns – the Deep Theory

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1d40b2c6-51b8-4239-8466-df690a18d28d.xhtml

- Monoids: reduce and fold; numbers.reduceLeft((a, b) => a + b; numbers.foldLeft(0)(_ + _)
- Functors: map; listFunctor.map(numbers)(_ * 2)
- Monads: flatMap: Seq("abc", "xyz").flatMap(_.toUpperCase())
