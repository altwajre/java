# Generalized type constraints

http://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html

The lowdown `Type1 <:< Type2` is that this pattern tells the compiler:
Make sure that Type1 is a subtype of Types, or else report an error.

There is another similar construct =:= which tells the compiler.
Make sure that Type1 is exactly the same as Type2 or else report an error.
