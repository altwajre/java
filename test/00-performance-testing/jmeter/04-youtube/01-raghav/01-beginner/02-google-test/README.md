# Create First Jmeter Test

https://www.youtube.com/watch?v=8loLHbhfyh0&index=2&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

Checkout OneNote `Personal/JMeter/Google Test`

> Create `Test Plan`

> Create `Thread Group` (Users)

- Right click `Test Plan`, `Add`, `Thread Group`
- Name: `Users`

> Add `HTTP Request` (GoogleHomePage)

- Right click `Thread Group` (Users), `Add`, `Sampler`, `HTTP Request`
- Name: `GoogleHomePage`, Server Name or IP: `www.google.com`, Method: `GET`, Path: `/`

> Add HTTP Header Manager

- Right click `HTTP Request` (GoogleHomePage), `Add`, `Config Element`, `HTTP Header Manager`

> Add `Listeners`

- Right click `HTTP Request` (GoogleHomePage), `Add`, `Listener`, `View Results in Table` and `View Results Tree`
