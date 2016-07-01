package com.company.app;

import java.sql.*;
import java.util.ArrayList;

class EmployeeDto{
    private int empNo;
    private String eName;
    private String jobTitle;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString(){
        return empNo + ", " + eName + ", " + jobTitle;
    }
}

public class App
{
    public static void main( String[] args )
    {
        String sqlQuery = "SELECT * from Employee";
        ArrayList<EmployeeDto> employees = new ArrayList<>();
        // Open autocloseable Connection, Statement and get the result set
        try(Connection conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Lesson21");
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sqlQuery)){
            // Process each column in the result set and print the data
            while(resultSet.next()){
                EmployeeDto employeeDto = new EmployeeDto();
                employeeDto.setEmpNo(resultSet.getInt("EMPNO"));
                employeeDto.seteName(resultSet.getString("ENAME"));
                employeeDto.setJobTitle(resultSet.getString("JOB_TITLE"));
                employees.add(employeeDto);
            }

            System.out.println("# Select all employees");
            employees.forEach(System.out::println);

            System.out.println("# Select one employee at a time");
            statementTest(employees, statement);

            preparedStatementTest(employees, conn);

            resultSetMetaDataTest(sqlQuery, statement);

            /*
          //  Transaction
          conn.setAutoCommit(false);
          Statement stmt = conn.createStatement();
          stmt.addBatch("insert into Orders " +
                      "values(123, 'Buy','IBM',200)");
          stmt.addBatch("insert into OrderDetail " +
                      "values('JSmith', 'Broker131', '05/20/02')");
          stmt.executeBatch();
          conn.commit(); // Transaction succeeded
             */
        }
        catch (SQLException e){
            System.out.println ("SQLError: " + e.getMessage ()
                    + " code: " + e.getErrorCode ());
            e.printStackTrace();
        }
    }

    private static void resultSetMetaDataTest(String sqlQuery, Statement statement) throws SQLException {
        System.out.println("# ResultSetMetaData");

        ResultSet rs = statement.executeQuery(sqlQuery);
        // Find out the number of columns, their names and display the data
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int colCount = rsMetaData.getColumnCount();
        for(int i = 1; i <= colCount; i++){
            System.out.print("Column name: " + rsMetaData.getColumnName(i) + " ");
        }
        System.out.println("");
        while(rs.next()){
            for(int i = 1; i <= colCount; i++){
                System.out.print(rs.getString(i) + " ");
            }
            System.out.println("");
        }
    }

    /*
    It is more efficient because it is pre-compiled SQL statement before executing it.
     */
    private static void preparedStatementTest(ArrayList<EmployeeDto> employees, Connection conn) throws SQLException {
        System.out.println("## preparedStatementTest");
        PreparedStatement preparedStatement = conn.prepareStatement("SELECT * from Employee WHERE empno=? and ename=?");
        for(int i = 0; i < employees.size(); i++){
            preparedStatement.setInt(1, employees.get(i).getEmpNo());
            preparedStatement.setString(2, employees.get(i).geteName());
            ResultSet resultSetSelect = preparedStatement.executeQuery();
            if(resultSetSelect.next()){
                int empNo = resultSetSelect.getInt("EMPNO");
                String eName = resultSetSelect.getString("ENAME");
                String jobTitle = resultSetSelect.getString("JOB_TITLE");
                System.out.println(empNo + ", " + eName + ", " + jobTitle);
            }
        }
    }

    private static void statementTest(ArrayList<EmployeeDto> employees, Statement statement) throws SQLException {
        System.out.println("## statementTest");
        for(int i = 0; i < employees.size(); i++){
            String sqlSelect = "SELECT * from Employee WHERE empno=" + employees.get(i).getEmpNo();
            ResultSet resultSetSelect = statement.executeQuery(sqlSelect);
            if(resultSetSelect.next()){
                int empNo = resultSetSelect.getInt("EMPNO");
                String eName = resultSetSelect.getString("ENAME");
                String jobTitle = resultSetSelect.getString("JOB_TITLE");
                System.out.println(empNo + ", " + eName + ", " + jobTitle);
            }
        }
    }
}
/*
output:
# Select all employees
7369, John Smith, Clerk
7499, Joe Allen, Salesman
7521, Mary Lou, Director
# Select one employee at a time
## statementTest
7369, John Smith, Clerk
7499, Joe Allen, Salesman
7521, Mary Lou, Director
## preparedStatementTest
7369, John Smith, Clerk
7499, Joe Allen, Salesman
7521, Mary Lou, Director
 */
