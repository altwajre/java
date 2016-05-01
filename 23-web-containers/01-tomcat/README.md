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

Tomcat whan$ bin/startup.sh

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

### Setup Tomcat in Eclipse

1, `Preferences`, `Server`, `Runtime Environments`, add `Apache Tomcat` with path `Library/Tomcat`

2, `Window`, `Web Browser`, select `1 Default system web browser`

