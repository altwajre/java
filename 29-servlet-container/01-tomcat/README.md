# Tomcat

https://www.youtube.com/watch?v=wIbJ7tc5oGE

## Download

https://tomcat.apache.org/download-90.cgi

> `Core`, `tar.gz`

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

Tomcat homepage is at http://localhost:8080/

## Manager App

click `Manager App` button, and login with username: admin, password: admin

## Deployment

copy `name.war` to `/Library/Tomcat/webapps`

go to http://localhost:8080/name/

### Undeploy

just delete `name.war` from `/Library/Tomcat/webapps`

## IntelliJ Ultimate

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

## Eclipse - Neon

https://www.youtube.com/watch?v=JkqLQjf2sc0

https://www.youtube.com/watch?v=h-yKgQtpbco

### Setup Tomcat in Eclipse

1, `Preferences`, `Server`, `Runtime Environments`, `Apache Tomcat v9.0`, `Create a new local server`, installation directory: `/Library/Tomcat`

2, `Window`, `Web Browser`, select `1 Default system web browser`

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

### Open existing project

1, File, Import
