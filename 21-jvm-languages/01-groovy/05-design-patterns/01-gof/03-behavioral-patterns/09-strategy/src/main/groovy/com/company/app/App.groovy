package com.company.app

// http://groovy-lang.org/design-patterns.html#_strategy_pattern
class Student {
    Student(name, age){
        Name = name
        Age = age
    }
    String Name
    int Age
}
interface Sort{
    void sort(List list)
}
class QuickSort implements Sort {
    void sort(List list) {
        println("QuickSort.sort()")
    }
}
class ShellSort implements Sort {
    void sort(List list) {
        println("ShellSort.sort()")
    }
}
class SortedList extends ArrayList<Student>{
    Sort SortStrategy
    void sortStudents(){
        SortStrategy.sort(this)
        this.each {println(" $it.name")}
        println('')
    }
}
class App {
    static void main(String... args){
        SortedList students = new SortedList()
        students.add(new Student("Tom", 18))
        students.add(new Student("Dick", 30))
        students.add(new Student("Harry", 10))

        students.SortStrategy = new QuickSort()
        students.sortStudents()

        students.SortStrategy = new ShellSort()
        students.sortStudents()
    }
}
/*
output:
QuickSort.sort()
 Tom
 Dick
 Harry

ShellSort.sort()
 Tom
 Dick
 Harry
 */
