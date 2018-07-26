package model

/*
Putting Common Code in Package Objects

https://www.safaribooksonline.com/library/view/scala-cookbook/9781449340292/ch06s08.html
 */
object PackageObjectDriver {
  def main(args: Array[String]): Unit = {

    // access model package objects
    echo("Hello")
    echo(MAGIC_NUM)
    echo(Margin.LEFT)

    // use
    var map = MutableMap("name" -> "Tom")
    map += ("password" -> "123")
    for((k, v) <- map) printf("key: %s, value: %s\n", k, v)
  }
}
/*
Hello
42
LEFT
key: name, value: Tom
key: password, value: 123
 */
