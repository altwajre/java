package com.company.app;

import java.sql.*;

public class App
{
    public static void main( String[] args )
    {
        Connection conn = null;
        String url = "jdbc:mysql://localhost/cookbook";
        String userName = "cbuser";
        String password = "cbpass";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("Connected");

            insertStatement(conn);

            selectStatement(conn);

            deleteStatement(conn);

            selectStatement(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (conn != null)
        {
            try
            {
                conn.close ();
                System.out.println ("Disconnected");
            }
            catch (Exception e) { /* ignore close errors */ }
        }
    }

    private static void deleteStatement(Connection conn) throws SQLException {
        System.out.println("\n#deleteStatement");
        PreparedStatement delete = conn.prepareStatement(
                "DELETE FROM profile WHERE name = ?"); // ? is placeholder
        delete.setString(1, "De'Mont");
        delete.executeUpdate();
        delete.close();
    }

    private static void selectStatement(Connection conn) throws SQLException {
        System.out.println("\n#selectStatement");
        PreparedStatement select = conn.prepareStatement("SELECT id, name, cats FROM profile");
        select.executeQuery();
        ResultSet rs = select.getResultSet();
        int count = 0;
        while(rs.next()){ // loop through rows of result set
            int id = rs.getInt(1); // extract columns 1, 2, and 3
            String name = rs.getString(2);
            int cats = rs.getInt(3);
            System.out.println("id: " + id + ", name: " + name + ", cats: " + cats);
            ++count;
        }
        rs.close(); // close result set
        select.close();
        System.out.println("Number of rows returned: " + count);
    }

    private static void insertStatement(Connection conn) throws SQLException {
        System.out.println("#insertStatement");
        PreparedStatement insert = conn.prepareStatement(
                "INSERT INTO profile (name,birth,color,foods,cats)"
                + " VALUES(?,?,?,?,?)"); // ? is placeholder
        insert.setString(1, "De'Mont");
        insert.setString(2, "1973-01-12");
        insert.setNull(3, Types.CHAR);
        insert.setString(4, "eggroll");
        insert.setInt(5, 4);
        insert.executeUpdate();
        insert.close();
    }
}
/*
Placeholders enable you to avoid writing data values literally in SQL statements. Using this approach, you write
statements using placeholders—special markers that indicate where the values go. Two common parameter markers
are ? and %s. Depending on the marker, rewrite the INSERT statement to use placeholders like this:
INSERT INTO profile (name,birth,color,foods,cats) VALUES(?,?,?,?,?)
INSERT INTO profile (name,birth,color,foods,cats) VALUES(%s,%s,%s,%s,%s)


output:
Connected
#insertStatement

#selectStatement
id: 1, name: Sybil, cats: 4
id: 2, name: Nancy, cats: 3
id: 3, name: Ralph, cats: 4
id: 4, name: Lothair, cats: 5
id: 5, name: Henry, cats: 1
id: 6, name: Aaron, cats: 1
id: 7, name: Joanna, cats: 0
id: 8, name: Stephen, cats: 0
id: 14, name: De'Mont, cats: 4
Number of rows returned: 9

#deleteStatement

#selectStatement
id: 1, name: Sybil, cats: 4
id: 2, name: Nancy, cats: 3
id: 3, name: Ralph, cats: 4
id: 4, name: Lothair, cats: 5
id: 5, name: Henry, cats: 1
id: 6, name: Aaron, cats: 1
id: 7, name: Joanna, cats: 0
id: 8, name: Stephen, cats: 0
Number of rows returned: 8
Disconnected
 */