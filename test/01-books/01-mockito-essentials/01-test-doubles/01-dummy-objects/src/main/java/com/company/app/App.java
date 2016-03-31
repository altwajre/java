package com.company.app;

import java.math.BigDecimal;
import java.util.List;

enum Grades{
    Excellent, VeryGood, Good, Average, Poor;
}
class Student{
    private final String roleNumber;
    private final String name;
    Student(String roleNumber, String name) {
        this.roleNumber = roleNumber;
        this.name = name;
    }
    public String getRoleNumber(){
        return roleNumber;
    }
    public String getName(){
        return name;
    }
}
class DummyStudent extends Student{
    DummyStudent() {
        super(null, null);
    }
    @Override
    public String getRoleNumber(){
        throw new RuntimeException("Dummy student");
    }
    @Override
    public String getName(){
        throw new RuntimeException("Dummy student");
    }
}
class Marks{
    private final Student student;
    private final String subjectId;
    private final BigDecimal marks;
    Marks(Student student, String subjectId, BigDecimal marks) {
        this.student = student;
        this.subjectId = subjectId;
        this.marks = marks;
    }
    public Student getStudent(){
        return student;
    }
    public String getSubjectId(){
        return subjectId;
    }
    public BigDecimal getMarks(){
        return marks;
    }
}
class Teacher{
    public BigDecimal calculatePercent(BigDecimal aggregate, int numberOfSubjects){
        BigDecimal percent = new BigDecimal(aggregate.doubleValue() / (100 * numberOfSubjects));
        return percent.multiply(new BigDecimal("100"));
    }
    public Grades generateGrade(List<Marks> marksList){
        BigDecimal aggregate = BigDecimal.ZERO;
        for(Marks mark : marksList){
            aggregate = aggregate.add(mark.getMarks());
        }
        BigDecimal percentage = calculatePercent(aggregate, marksList.size());
        if(percentage.compareTo(new BigDecimal("90.00")) > 0){
            return Grades.Excellent;
        }
        if(percentage.compareTo(new BigDecimal("75.00")) > 0){
            return Grades.VeryGood;
        }
        if(percentage.compareTo(new BigDecimal("60.00")) > 0){
            return Grades.Good;
        }
        if(percentage.compareTo(new BigDecimal("40")) > 0){
            return Grades.Average;
        }
        return Grades.Poor;
    }
}
/*
Examination grade system. The program will analyze the aggregate of all the subjects and determine the grade of a student.
 */
public class App
{
    public static void main( String[] args )
    {
        Teacher teacher = new Teacher();
        System.out.println(teacher.calculatePercent(new BigDecimal("90"), 2));
    }
}
