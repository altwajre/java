/*
Generalized type constraints

http://blog.bruchez.name/2015/11/generalized-type-constraints-in-scala.html

 */
object GeneralizedTypeConstraints {
  def tuple[T, U](t: T, u: U) = (t, u)
  // The compiler resolves it and obtains T = String and U = Any:
  def tupleIfSubtype[T <: U, U](t: T, u: U) = (t, u)

  def main(args: Array[String]) = {
//    val tuple = tuple("Lincoln", 42)
//    println(tuple)
    val tuple = tupleIfSubtype("Lincoln", 42)
    println(tuple)
  }
}
