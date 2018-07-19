package monoids

/*
When to use monoids

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0e4faaac-5bb2-4a62-8ab3-8c18bf1594ed.xhtml

 */
object FeatureCountingOneOff {
  def main(args: Array[String]): Unit = {
    val features = Array("hello", "features", "for", "ml", "hello", "for", "features", "hello")
    System.out.println(s"The features are: ${
      features.foldLeft(Map[String, Int]()) {
        case (res, feature) =>
          println(s"# res: $res")
          println(s"# feature: $feature")
          res.updated(feature, res.getOrElse(feature, 0) + 1)
      }
    }")
  }
}
/*
# res: Map()
# feature: hello
# res: Map(hello -> 1)
# feature: features
# res: Map(hello -> 1, features -> 1)
# feature: for
# res: Map(hello -> 1, features -> 1, for -> 1)
# feature: ml
# res: Map(hello -> 1, features -> 1, for -> 1, ml -> 1)
# feature: hello
# res: Map(hello -> 2, features -> 1, for -> 1, ml -> 1)
# feature: for
# res: Map(hello -> 2, features -> 1, for -> 2, ml -> 1)
# feature: features
# res: Map(hello -> 2, features -> 2, for -> 2, ml -> 1)
# feature: hello
The features are: Map(hello -> 3, features -> 2, for -> 2, ml -> 1)
 */
