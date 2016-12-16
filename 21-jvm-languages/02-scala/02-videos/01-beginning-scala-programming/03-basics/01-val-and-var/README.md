# val And var

- val are not reassignable
- var are reassignable
- var are rarely used
- types on val and var are implied
- types can always be explicitly defined in declarations

## val - as `final in Java`

*Reassign is not allowed*

```
scala> val a = 100
a: Int = 100

scala> a = 300
<console>:12: error: reassignment to val
       a = 300
         ^
```

*Explicitly define type*

```
scala> val a:Int = 400
a: Int = 400

scala> val a:Double = 400
a: Double = 400.0

scala> val a = 400:Double
a: Double = 400.0

```

*Type inference*

```
scala> val a = 100
a: Int = 100

scala> val b = "Hello"
b: String = Hello

scala> val c = b + ", " + a
c: String = Hello, 100
```

## var - as `variable in Java`

*Static type - changing type is not allowed*

```
scala> var b = 400
b: Int = 400

scala> b = 500
b: Int = 500

scala> b = "Hello"
<console>:12: error: type mismatch;
 found   : String("Hello")
 required: Int
       b = "Hello"
           ^
```
