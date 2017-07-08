package com.company.app;

import java.sql.*;

/*
select all
https://www.tutorialspoint.com/hsqldb/hsqldb_select_query.htm

select where
https://www.tutorialspoint.com/hsqldb/hsqldb_where_clause.htm

select like
https://www.tutorialspoint.com/hsqldb/hsqldb_like_clause.htm

sorting results
https://www.tutorialspoint.com/hsqldb/hsqldb_sorting_results.htm
 */
public class App
{
    public static void main( String[] args ) throws Exception {

        // Registering the HSQLDB JDBC driver
        Class.forName("org.hsqldb.jdbc.JDBCDriver");

        //Creating the connection with HSQLDB
        Connection connection = DriverManager.getConnection(
            "jdbc:hsqldb:hsql://localhost/testdb",
            "SA",
            "");
        Statement statement = connection.createStatement();

        selectAll(statement);

        selectWhere(statement);

        selectLike(statement);

        sortingResults(statement);
    }

    private static void sortingResults(Statement statement) throws SQLException {
        System.out.println("#sortingResults");
        String sorting = "SELECT id, title, author from tutorials_tbl ORDER BY author ASC;";
        ResultSet result = statement.executeQuery(sorting);

        while(result.next()){
            System.out.println(result.getInt("id")+" | "+
                result.getString("title")+" | "+
                result.getString("author"));
        }
    }
/*
output:
#sortingResults
102 | Learn MySQL | Abdul S
104 | Learn JDB | Ajith kumar
103 | Learn Excell | Bavya kanna
100 | Learn PHP | John Poul
101 | C and Data Structures | Yaswanth
 */

    private static void selectLike(Statement statement) throws SQLException {

        System.out.println("#selectLike");
        String selectLike = "SELECT * from tutorials_tbl WHERE author LIKE 'John%';";
        ResultSet result = statement.executeQuery(selectLike);

        while(result.next()){
            System.out.println(result.getInt("id")+" | "+
                result.getString("title")+" | "+
                result.getString("author"));
        }
    }
/*
output:
#selectWhere
100 | Learn PHP | John Poul
 */

    private static void selectWhere(Statement statement) throws SQLException {

        System.out.println("#selectWhere");
        String selectWhere = "SELECT id, title, author FROM tutorials_tbl WHERE title = 'Learn MySQL'";
        ResultSet result = statement.executeQuery(selectWhere);

        while(result.next()){
            System.out.println(result.getInt("id")+" | "+
                result.getString("title")+" | "+
                result.getString("author"));
        }
    }
/*
output:
#selectWhere
102 | Learn MySQL | Abdul S
 */
    private static void selectAll(Statement statement) throws SQLException {

        System.out.println("#selectAll");
        String selectAll = "SELECT id, title, author FROM tutorials_tbl";
        ResultSet result = statement.executeQuery(selectAll);

        while(result.next()){
            System.out.println(result.getInt("id")+" | "+
                result.getString("title")+" | "+
                result.getString("author"));
        }
    }
/*
output:
#selectAll
100 | Learn PHP | John Poul
101 | Learn C | Yaswanth
102 | Learn MySQL | Abdul S
103 | Learn Excell | Bavya kanna
104 | Learn JDB | Ajith kumar
105 | Learn Junit | Sathya Murthi
 */
}
