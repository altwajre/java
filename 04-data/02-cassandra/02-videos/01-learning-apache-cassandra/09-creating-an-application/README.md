# Creating An Application

should checkout videos on how use eclipse to application

> launch eclipse

vm1@ubuntu:~$ eclipse/eclipse

New, Dynamic Web Project, vehicle-tracker, next, next, check "Generate web.xml deployment descriptor", finish

Right click src, New, Servlet, com.vehicletracker.servlet, VehicleTrackerServlet, next, URL mappings: /track, next,
uncheck doPost, finish

Right click vehicle-tracker, select convert to maven project, Group Id: com.datastax.cassandra,
Artifact Id: cassandra-driver-core, Version: 2.0.2