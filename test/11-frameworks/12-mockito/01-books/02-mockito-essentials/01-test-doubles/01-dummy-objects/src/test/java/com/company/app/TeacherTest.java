package com.company.app;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
class DummyStudent extends Student{ // dummy object
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
public class TeacherTest {
    // The dummyStudent object is not used in the Teacher class or test method, but it is necessary for the Marks object.
    @Test
    public void when_marks_above_seventy_five_percent_returns_very_good(){
        DummyStudent dummyStudent = new DummyStudent();
        Marks inEnglish = new Marks(dummyStudent, "English-X", new BigDecimal("81.00"));
        Marks inMaths = new Marks(dummyStudent, "Math-X", new BigDecimal("97.00"));
        Marks inHistory = new Marks(dummyStudent, "History-X", new BigDecimal("79.00"));
        List<Marks> marks = new ArrayList<>();
        marks.add(inHistory);
        marks.add(inMaths);
        marks.add(inEnglish);
        Grades grade = new Teacher().generateGrade(marks);
        assertEquals(Grades.VeryGood, grade);
    }
}