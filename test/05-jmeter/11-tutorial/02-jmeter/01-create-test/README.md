# Create Test

https://www.youtube.com/watch?v=8loLHbhfyh0&index=2&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

## Server

Launch Server - App.main()

## Client - Jmeter

```
1, Start Jmeter
2, Create a TestPlan
3, Create Thread Group (Users)
4, Add a Sampler, Http Request
   - Server Name or IP: localhost
   - Port Number: 8080
   - Method: GET
   - /contacts
5, Add Listers
   View Results in Table
   View Results Tree
     - Response Data
6, To Run the Test
```
