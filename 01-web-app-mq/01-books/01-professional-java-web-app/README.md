# Professional Java for Web Applications

https://www.safaribooksonline.com/library/view/professional-java-for/9781118909317/

## Tomcat
 
### Start Server

location: `/Library/Tomcat/`

Tomcat whan$ bin/startup.sh - if it doesn't work, shutdown and startup again

Tomcat whan$ bin/shutdown.sh

Tomcat homepage is at http://localhost:8080/

## Eclipse

### Create maven Dynamic Web Project

https://www.youtube.com/watch?v=fWATrhZcCNI

1, `File`, `New`, `Project...`, expand `Web`, select `Dynamic Web Project` 

2, `Project name` webapp, `Target runtime` Apache Tomcat v9.0, `Next`, `Next`, check `Generate web.xml deployment descriptor`, `Finish`

3, Convert to Maven project - Right click on project, `Configure`, `Convert to Maven Project`, `Group Id:` com.company, `Packaging:` war, `Finish`

4, pom.xml - Add following

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

5, Servlet - `Java Resources/src`, `Class`, `Package:` com.company, `Name:` HelloServlet

```
package com.company;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/greeting", name="HelloServletx")
public class HelloServlet extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.getWriter().println("Hello from HelloServlet");
	}
}
```

6, Server - Right click on webapp project, `Run As`, `Run on Server`, `Finish`, `Restart server`

7, Client - Go to http://localhost:8080/webapp/greeting
