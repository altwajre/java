package _10_memo

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex
import scalaz.Memo

import scala.collection.mutable.Map

/*
# Memoization (caching)
https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0bc815d2-f4fe-444b-a6f8-25f55a9f987a.xhtml

Memoization is a mechanism of recording (caching) a function result based on its arguments in order to reduce computation in consecutive calls.

Example
https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/baa167e5-d4c6-45d5-a40b-b837d81161c8.xhtml

// Thread safe
val memoMd5Scalaz: String => String = Memo.immutableHashMapMemo {
  md5
}
 */

object MemoApp {
  // memo (caching)
  trait Memoizer {
    // Note: this is not thread safe.
    def memo[X, Y](f: X => Y): (X => Y) = {
      val cache = Map[X, Y]()
      (x: X) => cache.getOrElseUpdate(x, f(x))
    }
  }

  class Hasher extends Memoizer {
    def md5(input: String): String = {
      println(s"Calling md5 for $input.")
      new String(Hex.encodeHex(MessageDigest.getInstance("MD5").digest(input.getBytes())))
    }

    val memoMd5 = memo(md5)

    // Thread safe
    val memoMd5Scalaz: String => String = Memo.immutableHashMapMemo {
      md5
    }
  }

  def main(args: Array[String]): Unit = {
    memoMd5Test

    memoMd5ScalazTest
  }

  private def memoMd5ScalazTest = {
    println("# memoMd5ScalazTest")
    val hasher = new Hasher
    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
    println(s"MD5 for 'hello' is '${hasher.memoMd5Scalaz("hello")}'.")
    println(s"MD5 for 'bye1' is '${hasher.memoMd5Scalaz("bye1")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5Scalaz("bye")}'.")
  }

  private def memoMd5Test = {
    println("# memoMd5Test")
    val hasher = new Hasher
    println(s"MD5 for 'hello' is '${hasher.memoMd5("hello")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5("bye")}'.")
    println(s"MD5 for 'hello' is '${hasher.memoMd5("hello")}'.")
    println(s"MD5 for 'bye1' is '${hasher.memoMd5("bye1")}'.")
    println(s"MD5 for 'bye' is '${hasher.memoMd5("bye")}'.")
  }
}
/*
# memoMd5Test
Calling md5 for hello.
MD5 for 'hello' is '5d41402abc4b2a76b9719d911017c592'.
Calling md5 for bye.
MD5 for 'bye' is 'bfa99df33b137bc8fb5f5407d7e58da8'.
MD5 for 'hello' is '5d41402abc4b2a76b9719d911017c592'.
Calling md5 for bye1.
MD5 for 'bye1' is 'cbaba8e623612f9c913bad9d7ac31dd0'.
MD5 for 'bye' is 'bfa99df33b137bc8fb5f5407d7e58da8'.
# memoMd5ScalazTest
Calling md5 for hello.
MD5 for 'hello' is '5d41402abc4b2a76b9719d911017c592'.
Calling md5 for bye.
MD5 for 'bye' is 'bfa99df33b137bc8fb5f5407d7e58da8'.
MD5 for 'hello' is '5d41402abc4b2a76b9719d911017c592'.
Calling md5 for bye1.
MD5 for 'bye1' is 'cbaba8e623612f9c913bad9d7ac31dd0'.
MD5 for 'bye' is 'bfa99df33b137bc8fb5f5407d7e58da8'.
 */
