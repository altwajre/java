package com.company.app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

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
class StudentService{
    private HashMap<String, List<Student>> studentCourseMap = new HashMap<>();
    private StudentServiceSpy spy;
    public void setSpy(StudentServiceSpy spy){
        this.spy = spy;
    }
    public void enrollToCourse(String courseName, Student student){
        MethodInvocation invocation = new MethodInvocation();
        invocation.addParam(courseName).addParam(student).setMethod("enrollToCourse");
        spy.registerCall(invocation);
        List<Student> list = studentCourseMap.get(courseName);
        if(list == null){
            list = new ArrayList<>();
        }
        if(!list.contains(student)){
            list.add(student);
        }
        studentCourseMap.put(courseName, list);
    }
}
class MethodInvocation{
    private List<Object> params = new ArrayList<>();
    private Object returnedValue = null;
    private String method;
    public List<Object> getParams(){
        return params;
    }
    public MethodInvocation addParam(Object param){
        getParams().add(param);
        return this;
    }
    public Object getReturnedValue(){
        return returnedValue;
    }
    public MethodInvocation setReturnedValue(Object returnedValue){
        this.returnedValue = returnedValue;
        return this;
    }
    public String getMethod(){
        return method;
    }
    public MethodInvocation setMethod(String method){
        this.method = method;
        return this;
    }
}
class StudentServiceSpy{
    private Map<String, List<MethodInvocation>> invocationMap = new HashMap<>();
    public void registerCall(MethodInvocation invocation){
        List<MethodInvocation> list = invocationMap.get(invocation.getMethod());
        if(list == null){
            list = new ArrayList<>();
        }
        if(!list.contains(invocation)){
            list.add(invocation);
        }
        invocationMap.put(invocation.getMethod(), list);
    }
    public int invocation(String methodName){
        List<MethodInvocation> list = invocationMap.get(methodName);
        if(list == null){
            return 0;
        }
        return list.size();
    }
    public MethodInvocation arguments(String methodName, int invocationIndex){
        List<MethodInvocation> list = invocationMap.get(methodName);
        if(list == null || (invocationIndex > list.size())){
            return null;
        }
        return list.get(invocationIndex-1);
    }
}
public class StudentServiceTest {
    StudentService service = new StudentService();
    StudentServiceSpy spy = new StudentServiceSpy();
    @Test
    public void enrolls_students() throws Exception{
        Student bob = new Student("001", "Robert Anthony");
        Student roy = new Student("002", "Roy Noon");
        // set spy
        service.setSpy(spy);

        // invoke method
        service.enrollToCourse("english", bob);
        service.enrollToCourse("history", roy);

        // assert that the method was invoked twice
        assertEquals(2, spy.invocation("enrollToCourse"));

        // get the method arguments for the first call
        List<Object> methodArguments = spy.arguments("enrollToCourse", 1).getParams();

        // get the method arguments for the second call
        List<Object> methodArguments2 = spy.arguments("enrollToCourse", 2).getParams();

        // verify, Bob enrolled to english first
        assertEquals("english", methodArguments.get(0));
        assertEquals(bob, methodArguments.get(1));

        // verify Roy enrolled to history
        assertEquals("history", methodArguments2.get(0));
        assertEquals(roy, methodArguments2.get(1));
    }
}