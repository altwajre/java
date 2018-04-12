# Operator Overloading

- Operator Overloading is using operators as method names
- Do not abuse the power
- Operator Overloading will interpret the name into $name or $unicode
- You have been using operator overloading perhaps without realizing it

```
scala> def +(x:Int, y:Int) = x + y
$plus: (x: Int, y: Int)Int

scala> def ~#^&*(x:String) = x.size
$tilde$hash$up$amp$times: (x: String)Int

scala> 3 + 5
res13: Int = 8

scala> 3.+(5)
res14: Int = 8
```