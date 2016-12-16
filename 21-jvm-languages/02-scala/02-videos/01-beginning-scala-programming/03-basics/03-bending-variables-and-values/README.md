# Bending Variables And Values To Your Will

- val or var can have spaces, and other valid identifier characters, including OpChars if surrounded by a backtick (`)
- They may be referenced without a backtick if they are not reserved. Otherwise you will need to include backticks.
- Any val or var, without a backtick, can include a valid Scala identifier known as an OpChar if followed by an underscore (_)

*val variable with spaces*

```
scala> val `my grestest gift in dollars` = 500
my grestest gift in dollars: Int = 500
```

*VALID OPCHAR*

Unicode Characters from \u0020-\u007F

http://www.unicode.org/charts/PDF/U0000.pdf

```
scala> val isThisMySalaryPerYear_? = 5000
isThisMySalaryPerYear_?: Int = 5000

scala> val thisShouldBeMyRaise_! = isThisMySalaryPerYear_? + 20000
thisShouldBeMyRaise_!: Int = 25000
```

*UNICODE SYMBOL MATH*

http://www.fileformat.info/info/unicode/category/Sm/list.htm

*backtick for reserved words*

```
scala> val `import` = 10
import: Int = 10

scala> println(import)
<console>:1: error: illegal start of simple expression
println(import)
        ^

scala> println(`import`)
10
```
