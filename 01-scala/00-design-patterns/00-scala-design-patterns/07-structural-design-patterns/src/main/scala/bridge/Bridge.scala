package bridge

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex

/*
The bridge design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/00d540b1-a374-44ad-848e-969a84633225.xhtml

Decouple an abstraction from its implementation so that the two can vary independently.

https://www.youtube.com/watch?v=F1YQ7YRjttI

The Bridge Pattern is good for combination two kinds of things

 */
object Bridge {
  // Implementor
  trait Hasher {
    def hash(data: String): String
    protected def getDigest(algorithm: String, data: String) = {
      val crypt = MessageDigest.getInstance(algorithm)
      crypt.reset()
      crypt.update(data.getBytes("UTF-8"))
      crypt
    }
  }
  // Implementations
  class Sha1Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
  }
  class Sha256Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
  }
  class Md5Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("MD5", data).digest()))
  }

  // Abstraction
  abstract class PasswordConverter(hasher: Hasher) {
    def convert(password: String): String
  }
  class SimplePasswordConverter(hasher: Hasher) extends PasswordConverter(hasher) {
    override def convert(password: String): String = hasher.hash(password)
  }
  class SaltedPasswordConverter(salt: String, hasher: Hasher) extends PasswordConverter(hasher) {
    override def convert(password: String): String =
      hasher.hash(s"${salt}:${password}")
  }

  def main(args: Array[String]) = {
    val p1 = new SimplePasswordConverter(new Sha256Hasher)
    val p2 = new SimplePasswordConverter(new Md5Hasher)
    val p3 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha1Hasher)
    val p4 = new SaltedPasswordConverter("8jsdf32T^$%", new Sha256Hasher)

    println(s"'password' in SHA-256 is: ${p1.convert("password")}")
    println(s"'1234567890' in MD5 is: ${p2.convert("1234567890")}")
    println(s"'password' in salted SHA-1 is: ${p3.convert("password")}")
    println(s"'password' in salted SHA-256 is: ${p4.convert("password")}")
  }
}
/*
'password' in SHA-256 is: 5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8
'1234567890' in MD5 is: e807f1fcf82d132f9bb018ca6738a19f
'password' in salted SHA-1 is: 71147d2ecf154fb670e8af874b42f4cb9c60c8e3
'password' in salted SHA-256 is: 8c58a827d5329261e7e3c1c409f1ec8ee0fe8bfb4af0f8f1ffa203e1c84993e0
 */
