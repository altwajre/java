# jdbc

> truncate table

```
    Properties properties = new Properties();
    properties.setProperty("user", "test");
    properties.setProperty("password", "test");
    properties.setProperty("useSSL", "false");
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/test_db", properties);
    Statement statement = connection.createStatement();
    statement.executeUpdate("TRUNCATE TABLE journal");
    statement.executeUpdate("TRUNCATE TABLE resources");
```
