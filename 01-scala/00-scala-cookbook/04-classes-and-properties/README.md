# Classes and Properties

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch04.html

Scala does not follow the JavaBeans naming convention for accessor and mutator methods.

- use foreach to print fields; when address is a None, calling foreach has not harm

```
person.address.foreach(a => {
  println(a.city)
  println(a.state)
  println(a.zip)
})
```
