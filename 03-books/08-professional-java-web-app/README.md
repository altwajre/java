# Professional Java for Web Applications

## Eclipse

### Create maven Dynamic Web Project

https://www.youtube.com/watch?v=fWATrhZcCNI

1, Create a new `Dynamic Web Project` named `webapp`, next, check `Generate web.xml`

2, Right click on project, Configure, `Convert to Maven Project`, Packaging: war, Finish

3, Add following to pom.xml

```
    <dependencies>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
```

4, Add following HelloServlet.java to `Java Resources/src`

```
package webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.getWriter().println("Hello, World!");
    }
}
```

5, Add following to `WebContent/WEB-INF/web.xml`

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>Hello</display-name>
    <servlet>
        <servlet-name>helloServlet</servlet-name>
        <servlet-class>webapp.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloServlet</servlet-name>
        <url-pattern>/greeting</url-pattern>
    </servlet-mapping>
</web-app>
```

6, Right click on webapp project, Run As, Run on Server, Finish, Restart server

7, Go to http://localhost:8080/webapp/greeting
