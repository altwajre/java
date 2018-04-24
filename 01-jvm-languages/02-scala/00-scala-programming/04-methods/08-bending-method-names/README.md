# Bending Method Names To Your Will

- Methods can have spaces, and other valid identifier characters, including OpChars if surrounded by a backtick (`)
- Methods may be referenced without a backtick if they are not reserved. Otherwise you will need to include backticks.
- Any method, without a backtick, can include an OpChar if followed by an underscore (_)

```
scala> def `Summation of`(x:Int, y:Int) = x + y
Summation$u0020of: (x: Int, y: Int)Int

scala> `Summation of`(2, 3)
res12: Int = 5

scala> def areWeLikingScala_? = true
areWeLikingScala_$qmark: Boolean

scala> val status = if(areWeLikingScala_?) "Nice" else "Ugh!"
status: String = Nice

scala> def `return` (x:Double, y:Double) = (x+y+4.0).toString
return: (x: Double, y: Double)String

scala> val g = `return`(2.0, 3)
g: String = 9.0
```
