import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String... args) throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhhost\\PSQL;databaseName=Catalog;integratedSecurity=true", "", "");
        System.out.println("test");
        Statement sta = conn.createStatement();
        String Sql = "Select * from Product";
        ResultSet rs = sta.executeQuery(Sql);
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }
}
