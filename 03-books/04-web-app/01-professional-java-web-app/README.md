# Professional Java for Web Applications

## Tomcat
 
### Start Server

location: `/Library/Tomcat/`

Tomcat whan$ bin/startup.sh - if it doesn't work, shutdown and startup again

Tomcat whan$ bin/shutdown.sh

Tomcat homepage is at http://localhost:8080/

## Eclipse

### Create maven Dynamic Web Project

https://www.youtube.com/watch?v=fWATrhZcCNI

1, `File`, `New`, `Project...`, expand `Web`, select `Dynamic Web Project` named `webapp`, `Next`, `Next`, check `Generate web.xml deployment descriptor`, `Finish`

2, Convert to Maven project - Right click on project, `Configure`, `Convert to Maven Project`, `Group Id:` com.company, `Packaging:` war, `Finish`

3, pom.xml - Add following

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

4, Servlet - `Java Resources/src`, `Class`, `Package:` com.company, `Name:` HelloServlet

```
package webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.getWriter().println("Hello, World!");
    }
}
```

5, Configuring Deployment - Add following to `WebContent/WEB-INF/web.xml`

```
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" id="WebApp_ID" version="3.1">
  <display-name>Hello</display-name>
    <servlet>
        <servlet-name>helloServlet</servlet-name>
        <servlet-class>com.company.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>helloServlet</servlet-name>
        <url-pattern>/greeting</url-pattern>
    </servlet-mapping>
</web-app>
```

6, Server - Right click on webapp project, `Run As`, `Run on Server`, `Finish`, `Restart server`

7, Client - Go to http://localhost:8080/webapp/greeting
