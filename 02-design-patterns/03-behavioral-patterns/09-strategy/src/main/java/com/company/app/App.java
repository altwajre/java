package com.company.app;

import java.util.ArrayList;
import java.util.List;

public class App
{
    static class Student{
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
    static class QuickSort implements ISortStrategy{  // Concrete Strategy
        public void sort(List<Student> list) { System.out.println("QuickSorted list "); }
    }
    static class ShellSort implements ISortStrategy{  // Concrete Strategy
        public void sort(List<Student> list) {
            System.out.println("ShellSort list");
        }
    }
    static class MergeSort implements ISortStrategy{  // Concrete Strategy
        public void sort(List<Student> list) {
            System.out.println("MergeSort list");
        }
    }
    static class SortedList extends ArrayList<Student>{  // Context
        public ISortStrategy SortStrategy;
        public void sortStudents(){
            this.SortStrategy.sort(this);
            for(Student student : this){
                System.out.format(" %s\n", student.Name);
            }
            System.out.println("");
        }
    }
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
