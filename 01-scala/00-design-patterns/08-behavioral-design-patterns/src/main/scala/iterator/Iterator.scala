package iterator

import scala.collection.mutable.ListBuffer

/*
The iterator design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/d572f3cd-a029-4274-a18f-87d447fa88b1.xhtml

 */
object Iterator {
  case class Student(name: String, age: Int)
  class StudentIterator(students: Array[Student]) extends Iterator[Student] {
    var currentPos = 0

    override def hasNext: Boolean = currentPos < students.size

    override def next(): Student = {
      val result = students(currentPos)
      currentPos = currentPos + 1
      result
    }
  }
  class ClassRoom extends Iterable[Student] {
    val students: ListBuffer[Student] = ListBuffer[Student]()
    def add(student: Student) = {
//      student +=: students
      students += student
    }

    override def iterator: Iterator[Student] = new StudentIterator(students.toArray)
  }

  def main(args: Array[String]) = {
    val classRoom = new ClassRoom
    classRoom.add(Student("Tom", 28))
    classRoom.add(Student("Dick", 18))
    classRoom.add(Student("Harry", 38))
    classRoom.foreach(println)
  }
}
/*
Student(Harry,38)
Student(Dick,18)
Student(Tom,28)
 */
