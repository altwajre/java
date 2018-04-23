# Jmeter Test

- Create JMeter Test
https://www.youtube.com/watch?v=8loLHbhfyh0&index=2&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c
- Command Line
https://www.youtube.com/watch?v=K26q5VgwLKk&index=7&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

jmeter -n -t google.jmx -l Report.jtl

-n -> non gui mode
-t -> location of jmeter script
-l -> location of result file

jmeter -h
jmeter -?

## Server

00-server/node

node server.js

## Client - Jmeter

> Run Command-line Test

touch Report.jtl
jmeter -n -t Script.jmx -l Report.jtl

> View Report in GUI

- Click `Open`, select `Script.jmx`
- Select `Aggregate Report`, click `Browse...`, select `Report.jtl`

### GUI Mode

Checkout OneNote `Personal/JMeter/Http Request`

> Start Jmeter

- go to `/Applications/apache-jmeter-3.3/bin`, double click `jmeter.sh`

> Create a `Test Plan`

- Rename `Test Plan` `Name` to `MyTestPlan`

> Create `Thread Group` (Users)

- Right click `MyTestPlan`, `Add`, `Threads (Users)`, `Thread Group`
- Rename `Thread Group` `Name` to `Users`

> Add HTTP Request

- Right click `Thread Group (Users)`, `Add`, `Sampler`, `HTTP Request`
- Rename `HTTP Request` `Name` to `GET`
   - Server Name or IP: localhost
   - Port Number: 8080
   - Method: GET
   - /contacts

> Add HTTP Header Manager

- Right click `HTTP Request`, `Add`, `Config Element`, `HTTP Header Manager`

> Add Listers

- Right click `Thread Group (Users)`
- Add, Listeners, `View Results in Table`, `View Results Tree`, `Aggregate Report`
   - Response Data

> Run UI Test

- Right `Users` group, click `Start`
- Or click `Play` button
- Click `Clear all` button to clear the test result on `Table` and `Tree`
