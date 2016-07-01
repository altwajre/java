package com.company.app;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyJDBCExecutor{
    String sqlStatement();
    boolean transactionRequired() default false;
    boolean notifyOnUpdates() default false;
}
class Employee{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class HRBrowser{
    @MyJDBCExecutor(sqlStatement = "Select * from Employee")
    public List<Employee> getEmployees(){
        // Generate the code to get the data from DBMS place them in Arraylist and
        // return them to the caller of my getEmployees
        return new ArrayList<Employee>();
    }
    @MyJDBCExecutor(sqlStatement = "Update Employee set bonus=1000",
    transactionRequired = true,
    notifyOnUpdates = true)
    public void updateDate(){
        // JDBC code to perform transactional updates and notifications goes here
    }
}
public class App
{
    @SuppressWarnings("rawtypes")
    public static void main( String[] args )
    {
        String classWithAnnotation = "com.company.app.HRBrowser";
        // load the provided class
        try{
            Class loadedClass = Class.forName(classWithAnnotation);
            // references to class methods
            Method[] methods = loadedClass.getMethods();

            for(Method m : methods){
                if (m.isAnnotationPresent(MyJDBCExecutor.class)){
                    MyJDBCExecutor jdbcAnnotation = m.getAnnotation(MyJDBCExecutor.class);

                    System.out.println("Method: " + m.getName() +
                            ". Parameters of MyJDBCGenerator are: " +
                            "sqlStatement="+ jdbcAnnotation.sqlStatement() +
                            ", notifyOnUpdates="+ jdbcAnnotation.notifyOnUpdates() +
                            ", transactionRequired="+ jdbcAnnotation.transactionRequired());
                }
            }
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
/*
output:
Method: getEmployees. Parameters of MyJDBCGenerator are: sqlStatement=Select * from Employee, notifyOnUpdates=false, transactionRequired=false
Method: updateDate. Parameters of MyJDBCGenerator are: sqlStatement=Update Employee set bonus=1000, notifyOnUpdates=true, transactionRequired=true
 */
