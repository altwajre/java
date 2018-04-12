# Strings and Groovy Strings

String - '' single quote does not evaluate expression
Groovy String - "" double quote evaluate expression
```
groovy> def s = 'this is a string ${1 + 1}'
groovy> println s
this is a string ${1 + 1}

groovy> println s.getClass().getName()
java.lang.String

groovy> s = "this is a string ${1 + 1}"
groovy> println s
this is a string 2

groovy> println s.getClass().getName()
org.codehaus.groovy.runtime.GStringImpl
```

Operators
```
groovy> def s = 'this is a string' 
groovy> s + ' and more' 
Result: this is a string and more

groovy> def s = 'this is a string' 
groovy> s - 'is' - 'is'   // remove two is
Result: th  a string

groovy> def s = 'abc' 
groovy> s * 3 
Result: abcabcabc

groovy> def s = 'this is a string' 
groovy> s[0]  // first letter
Result: t

groovy> def s = 'this is a string' 
groovy> s[-1]  // last letter
Result: g

groovy> def s = 'this is a string' 
groovy> s[0..3]  // range
Result: this

groovy> def s = 'this is a string' 
groovy> s[-1..-3] 
Result: gni

groovy> def s = 'this is a string' 
groovy> s[0..3,5..6,8,10..-1] 
Result: thisisastring
```
