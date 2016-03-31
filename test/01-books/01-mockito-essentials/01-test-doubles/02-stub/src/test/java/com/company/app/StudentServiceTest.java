package com.company.app;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

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

class CreateStudentResponse {
    private final String errorMessage;
    private final Student student;

    CreateStudentResponse(String errorMessage, Student student) {
        this.errorMessage = errorMessage;
        this.student = student;
    }

    public boolean isSuccess() {
        return null == errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Student getStudent() {
        return student;
    }
}

interface StudentDao {
    String create(String name, String className) throws SQLException;
}

interface StudentService {
    CreateStudentResponse create(String name, String studentOfClass);
}

class StudentServiceImpl implements StudentService {
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

class ConnectionTimeoutStudentDaoStub implements StudentDao { // stub object
    public String create(String name, String className) throws SQLException {
        throw new SQLException("DB connection timed out");
    }
}

/*
The error condition is stubbed and passed into the service implementation object. When the service implementation invokes
the create() method on the stubbed DAO, it throws an SQLException error.
 */
public class StudentServiceTest {
    StudentService studentService;

    @Test
    public void when_connection_times_out_then_the_student_is_not_saved() {
        studentService = new StudentServiceImpl(new ConnectionTimeoutStudentDaoStub());
        String classNine = "IX";
        String johnSmith = "John Smith";
        CreateStudentResponse response = studentService.create(johnSmith, classNine);
        assertFalse(response.isSuccess());
    }
}