package gof.behavioral.strategy;

import java.util.ArrayList;
import java.util.List;

/*
Definition
Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary
independently from clients that use it.
 */
class Student{
  public Student(String name, String ssn){
    this.Name = name;
    this.Ssn = ssn;
  }
  public String Name;
  public String Ssn;
}
interface ISortStrategy{  // Strategy interface
  void sort(List<Student> list);
}
class QuickSort implements ISortStrategy{  // Concrete Strategy
  public void sort(List<Student> list) { System.out.println("QuickSorted list "); }
}
class ShellSort implements ISortStrategy{  // Concrete Strategy
  public void sort(List<Student> list) {
    System.out.println("ShellSort list");
  }
}
class MergeSort implements ISortStrategy{  // Concrete Strategy
  public void sort(List<Student> list) {
    System.out.println("MergeSort list");
  }
}
class SortedList extends ArrayList<Student> {  // Context
  public ISortStrategy SortStrategy;
  public void sortStudents(){
    this.SortStrategy.sort(this);
    for(Student student : this){
      System.out.format(" %s\n", student.Name);
    }
    System.out.println("");
  }
}
public class RealWorld {
  public static void main( String[] args )
  {
    SortedList studentRecords = new SortedList();
    studentRecords.add(new Student("Samual", "154-33-2009"));
    studentRecords.add(new Student("Jimmy", "487-43-1665"));
    studentRecords.add(new Student("Sandra", "655-00-2944"));
    studentRecords.add(new Student("Vivek", "133-98-8399"));
    studentRecords.add(new Student("Anna", "760-94-9844"));
    studentRecords.SortStrategy = new QuickSort();
    studentRecords.sortStudents();
    studentRecords.SortStrategy = new ShellSort();
    studentRecords.sortStudents();
    studentRecords.SortStrategy = new MergeSort();
    studentRecords.sortStudents();
  }
}
/*
QuickSorted list
 Samual
 Jimmy
 Sandra
 Vivek
 Anna

ShellSort list
 Samual
 Jimmy
 Sandra
 Vivek
 Anna

MergeSort list
 Samual
 Jimmy
 Sandra
 Vivek
 Anna
 */
