# Tomcat

https://wolfpaulus.com/journal/mac/tomcat8/

https://www.youtube.com/watch?v=wIbJ7tc5oGE

## Configuration

add `<user username="admin" password="admin" roles="manager-gui,admin-gui" />` to `conf/tomcat-users.xml`

add following to `conf/web.xml`

```
        <init-param>
          <param-name>compilerSourceVM</param-name>
          <param-value>1.8</param-value>
        </init-param>
        <init-param>
          <param-name>compilerTargetVM</param-name>
          <param-value>1.8</param-value>
        </init-param>
```

## Start Server

location: `/Library/Tomcat/`

Tomcat whan$ bin/startup.sh - if it doesn't work, shutdown and startup again

Tomcat whan$ bin/shutdown.sh

## Manager App

click `Manager App` button, and login with username: admin, password: admin

## Deployment

copy `name.war` to `/Library/Tomcat/webapps`

go to http://localhost:8080/name/

### Undeploy

just delete `name.war` from `/Library/Tomcat/webapps`

## IntelliJ

### Setup Tomcat in IntelliJ

1, open `Preferences` dialog

2, `Build, Execution, Deployment`, `Application Servers`

3, click `+`, select `Tomcat Server`, enter path `/Library/Tomcat`

4, Open browser `http://localhost:8080/sample-debug/`

### Add Tomcat Configuration to Project

1, click `run/debug`, click `Edit Configurations`

2, click '+', select `Tomcat Server`, click `Local`, `Name: Tomcat`

3, select `*:war exploded` for Deployment tab when there is a warning no artifacts are marked

4, on Deployment tag, `Application context: /sample-debug`

### Debug

1, shutdown tomcat server first by `Tomcat whan$ bin/shutdown.sh`

2, set breakpoints at `web/index.jsp`

3, click `Debug`

## Eclipse

https://www.youtube.com/watch?v=JkqLQjf2sc0

https://www.youtube.com/watch?v=h-yKgQtpbco

### Setup Tomcat in Eclipse

1, `Preferences`, `Server`, `Runtime Environments`, add `Apache Tomcat` with path `Library/Tomcat`

2, `Window`, `Web Browser`, select `1 Default system web browser`

### Create a new web project

1, File, New, Dynamic Web Project

2, Project name: hello, next, check `Generate web.xml deployment descriptor`, finish

3, Add new class by right click `Java Resources.src`, New, Class

4, Package: com.company  Name: HelloServlet  finish

```
package com.company;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class HelloServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.getWriter().println("Hello world!");
	}
}
```

5, Replace following in web.xml web-app tag

```
<display-name>Hello World Application</display-name>
<servlet>
    <servlet-name>helloServlet</servlet-name>
    <servlet-class>com.company.HelloServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>helloServlet</servlet-name>
    <url-pattern>/greeting</url-pattern>
</servlet-mapping>
```

6, Right click project `Hello`, Run As, Run On Server

7, Choose an existing server, select Tomcat, next, ensure it is deployed, finish

8, Select Restart server, click OK

9, Go to http://localhost:8080/hello/greeting

### Open existing project

1, File, Import

