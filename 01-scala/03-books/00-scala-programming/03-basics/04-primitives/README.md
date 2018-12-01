# byte, short, int, long, Float, Double, Character, Booleans

- Byte, Short, Int, Long, Float, Double, Char, Boolean have a correlation to Java's primitives
- Primitive assignments are inferred
- You can assign fields manually
- Primitive may be wrapped to give extra functionality

*byte - less than 128*

```
scala> val b:Byte = 10
b: Byte = 10

scala> val b = 10:Byte
b: Byte = 10

scala> val b:Byte = 128
<console>:11: error: type mismatch;
 found   : Int(128)
 required: Byte
       val b:Byte = 128
                    ^
```

*short - 32767*

```
scala> val maxShort:Short = 32767
maxShort: Short = 32767

scala> val maxShort:Short = 32768
<console>:11: error: type mismatch;
 found   : Int(32768)
 required: Short
       val maxShort:Short = 32768
                            ^
```

*int*

```
scala> val intValue = 30102
intValue: Int = 30102

scala> val intValue:Int = 30102
intValue: Int = 30102

scala> val intValue = 30102:Int
intValue: Int = 30102
```

*long*

```
scala> val g:Long = 3010233
g: Long = 3010233

scala> val g = 3010233:Long
g: Long = 3010233

scala> val g = 3010233L
g: Long = 3010233
```

*Floats - need to add f or F at the end of the value*

```
scala> val floatValue = 32.0f
floatValue: Float = 32.0

scala> val floatValue = 32.0F
floatValue: Float = 32.0

scala> val floatValue:Float = 32.0
<console>:11: error: type mismatch;
 found   : Double(32.0)
 required: Float
       val floatValue:Float = 32.0
                              ^

scala> val floatValue:Float = 32.0f
floatValue: Float = 32.0

scala> val floatValue = 32.0:Float
<console>:11: error: type mismatch;
 found   : Double(32.0)
 required: Float
       val floatValue = 32.0:Float
                        ^

scala> val floatValue = 32.0f:Float
floatValue: Float = 32.0
```

*Doubles*

```
scala> val d = 1023.04
d: Double = 1023.04

scala> val d = 1023.04:Double
d: Double = 1023.04

scala> val d:Double = 1023.04
d: Double = 1023.04

scala> val d:Double = 1023.04D
d: Double = 1023.04
```

*Character*

```
scala> val c = 'k'
c: Char = k

scala> val c2 = '\u03B8' <- unicode characters
c2: Char = θ
```

*Boolean*

```
scala> val b = true
b: Boolean = true

scala> val b2 = false
b2: Boolean = false
```

*others - treat everything object*

```
scala> 1 + 4
res7: Int = 5

scala> 1.+(4)
res8: Int = 5

scala> -5.abs
res9: Int = 5

scala> Math.abs(-5)
res10: Int = 5
```
