package com.company.app;

import java.sql.SQLException;

class Student {
    private final String roleNumber;
    private final String name;
    public Student(String roleNumber, String name) {
        this.roleNumber = roleNumber;
        this.name = name;
    }
    public String getRoleNumber() {
        return roleNumber;
    }
    public String getName() {
        return name;
    }
}
class CreateStudentResponse{
    private final String errorMessage;
    private final Student student;
    CreateStudentResponse(String errorMessage, Student student) {
        this.errorMessage = errorMessage;
        this.student = student;
    }
    public boolean isSuccess(){
        return null == errorMessage;
    }
    public String getErrorMessage(){
        return errorMessage;
    }
    public Student getStudent(){
        return student;
    }
}
interface StudentDao{
    String create(String name, String className) throws SQLException;
}
interface StudentService{
    CreateStudentResponse create(String name, String studentOfClass);
}
class StudentServiceImpl implements StudentService{
    private final StudentDao studentDao;
    StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    public CreateStudentResponse create(String name, String studentOfClass) {
        CreateStudentResponse response = null;
        try {
            String roleNum = studentDao.create(name, studentOfClass);
            response = new CreateStudentResponse(null, new Student(roleNum, name));
        } catch (SQLException e) {
            response = new CreateStudentResponse(e.getMessage(), null);
        }
        return response;
    }
}
public class App
{
    public static void main( String[] args )
    {
        // Run StudentServiceTest in test package
    }
}
