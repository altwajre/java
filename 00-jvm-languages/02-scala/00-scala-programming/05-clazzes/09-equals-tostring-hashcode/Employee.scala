import scala.beans.BeanProperty

class Employee(@BeanProperty val firstName: String,
               @BeanProperty val lastName: String,
               val title: String = "Programmer") {
  require(firstName.nonEmpty, "First Name cannot be empty")
  require(lastName.nonEmpty, "Last Name cannot be empty")
  require(title.nonEmpty, "Title cannot be empty")

  if (title.contains("Senior") || title.contains("Junior"))
    throw new IllegalArgumentException("Title cannot contain Junior or Senior")

  def fullName = s"$firstName $lastName"

  def copy(firstName: String = this.firstName,
           lastName: String = this.lastName,
           title: String = this.title) = {
    new Employee(firstName, lastName, title)
  }

  override def equals(obj: scala.Any): Boolean = {
    if (!obj.isInstanceOf[Employee]) false
    else {
      val other = obj.asInstanceOf[Employee]
      other.firstName == this.firstName &&
        other.lastName == this.lastName &&
        other.title == this.title
    }
  }

  override def hashCode(): Int = {
    var result = 19
    result = 31 * result + firstName.hashCode
    result = 31 * result + lastName.hashCode
    result = 31 * result + title.hashCode
    result
  }
}
