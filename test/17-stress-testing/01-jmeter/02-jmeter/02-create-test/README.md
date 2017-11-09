# How to create first Jmeter Test

https://www.youtube.com/watch?v=8loLHbhfyh0&index=2&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

## Server

Launch Server - App.main()

## Client - Jmeter

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

- Right click `Thread Group (Users)`, `Add`, `Listener`, `View Results in Table` and add `View Results Tree`
     - Response Data

> Run Test

- Right `Users` group, click `Start`
- Or click `Play` button
- Click `Clear all` button to clear the test result on `Table` and `Tree`
