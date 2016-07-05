# glassfish

## download

https://glassfish.java.net/download.html

move glassfish4 to `/Users/whan/glassfish4`

## Eclipse

install glassish4 plugin

### Create maven Dynamic Web Project

https://www.youtube.com/watch?v=fWATrhZcCNI

1, `File`, `New`, `Project...`, expand `Web`, select `Dynamic Web Project` 

2, `Project name:` webapp, `Target runtime` GlassFish 4, `Next`, `Next`, check `Generate web.xml deployment descriptor`, `Finish`

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
