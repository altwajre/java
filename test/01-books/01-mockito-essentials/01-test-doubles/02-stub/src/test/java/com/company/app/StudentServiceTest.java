package com.company.app;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertFalse;

class ConnectionTimeoutStudentDaoStub implements StudentDao{
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
    public void when_connection_times_out_then_the_student_is_not_saved(){
        studentService = new StudentServiceImpl(new ConnectionTimeoutStudentDaoStub());
        String classNine = "IX";
        String johnSmith = "John Smith";
        CreateStudentResponse response = studentService.create(johnSmith, classNine);
        assertFalse(response.isSuccess());
    }
}