import java.sql.*;

public class App {
  public static void main(String[] args) {
    Connection conn = null;
    String url = "jdbc:mysql://localhost/test?useSSL=false&allowPublicKeyRetrieval=true";
    String userName = "test";
    String password = "test";

    try {
//      Class.forName("com.mysql.jdbc.Driver"); // old
      Class.forName("com.mysql.cj.jdbc.Driver"); // run Driver static block code.
      conn = DriverManager.getConnection(url, userName, password);
      System.out.println("Connected");

//      changeInformation(conn);

      retrieveInformation(conn);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Cannot connect to server");
      System.exit(1);
    }
    if (conn != null) {
      try {
        conn.close();
        System.out.println("Disconnected");
      } catch (Exception e) { /* ignore close errors */ }
    }
  }

  private static void retrieveInformation(Connection conn) throws SQLException {
    System.out.println("\n#retrieveInformation");
    Statement s = conn.createStatement();
    s.executeQuery("SELECT id, name, cats FROM profile");
    ResultSet rs = s.getResultSet();
    int count = 0;
    while (rs.next()) { // loop through rows of result set
      int id = rs.getInt(1); // extract columns 1, 2, and 3
      String name = rs.getString(2);
      int cats = rs.getInt(3);
      System.out.println("id: " + id + ", name: " + name + ", cats: " + cats);
      ++count;
    }
    rs.close(); // close result set
    s.close(); // close statement
    System.out.println("Number of rows returned: " + count);
  }

  private static void changeInformation(Connection conn) throws SQLException {
    System.out.println("\n#changeInformation");
    Statement s = conn.createStatement();
    s.execute("USE cookbook");
    int count = s.executeUpdate("UPDATE profile SET cats = cats+1 WHERE name = 'Sybil'");
    s.close();
    System.out.println("Number of rows updated: " + count);
  }
}
/*
Connected

#retrieveInformation
id: 1, name: Sybil, cats: 0
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
