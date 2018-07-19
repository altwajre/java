package _03_pimp

package object pimp {
  implicit class StringExtensions(val s: String) extends AnyVal {
    def isAllUpperCase: Boolean =
      !(0 until s.length).exists {
        case index =>
          s.charAt(index).isLower
      }
  }
}

