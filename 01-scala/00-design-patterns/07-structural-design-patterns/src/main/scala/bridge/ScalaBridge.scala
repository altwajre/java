package bridge

import java.security.MessageDigest

import org.apache.commons.codec.binary.Hex

/*
The bridge design pattern the Scala way

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/12878710-956e-4930-b84e-b984559bee7a.xhtml

 */
object ScalaBridge {
  trait Hasher {
    def hash(data: String): String

    protected def getDigest(algorithm: String, data: String) = {
      val crypt = MessageDigest.getInstance(algorithm)
      crypt.reset()
      crypt.update(data.getBytes("UTF-8"))
      crypt
    }
  }

  trait Sha1Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-1", data).digest()))
  }

  trait Sha256Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("SHA-256", data).digest()))
  }

  trait Md5Hasher extends Hasher {
    override def hash(data: String): String =
      new String(Hex.encodeHex(getDigest("MD5", data).digest()))
  }

  abstract class PasswordConverterBase {
    self: Hasher => // Self type
    def convert(password: String): String
  }

  class SimplePasswordConverterScala extends PasswordConverterBase {
    self: Hasher =>
    override def convert(password: String): String = hash(password)
  }

  class SaltedPasswordConverterScala(salt: String) extends PasswordConverterBase {
    self: Hasher =>
    override def convert(password: String): String = hash(s"${salt}:${password}")
  }

  def main(args: Array[String]) = {
    val p1 = new SimplePasswordConverterScala with Sha256Hasher
    val p2 = new SimplePasswordConverterScala with Md5Hasher
    val p3 = new SaltedPasswordConverterScala("8jsdf32T^$%") with Sha1Hasher
    val p4 = new SaltedPasswordConverterScala("8jsdf32T^$%") with Sha256Hasher

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
