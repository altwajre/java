# Tutorial

https://www.youtube.com/watch?v=M-iAXz8vs48&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

**Create First Test**

https://www.youtube.com/watch?v=8loLHbhfyh0&index=2&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

```
1, Start Jmeter
2, Create a TestPlan
3, Create Thread Group (Users)
4, Add a Sampler (Http)
5, Add Listers
   View Results in Table
   View Results Tree
6, To Run the Test
```

**Assertions**

https://www.youtube.com/watch?v=mXhC9CtQBC8&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=3

Use assertion to check the Response
 
```
1, Response Assertion
2, Duration Assertion
3, Size Assertion
4, HTML Assertion
5, XML Assertion
6, XPATH Assertion
```

**Listeners**

https://www.youtube.com/watch?v=5FyVKVAqEJo&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=4

```
1, View Results in Table
2, View Results Tree
3, Aggregate Report
4, Graph Results
5, Summary Report
6, Simple Data Writer
```

**Record UI**

https://www.youtube.com/watch?v=JI99ZOuI5tQ&index=5&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

```
1, Tools available for recording Jmeter UI test
   - Badboy software (Windows)
   - Blazemeter - Chrome Plugin - (Windows and Mac)
2, Record a Test
3, Export as Jmeter (.jmx) Script
4, Open the script in Jmeter
5, Add listeners
6, Run and validate
```

**Database**

https://www.youtube.com/watch?v=oy53KAKHpts&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=6

```
1, Add mysql JDBC jar to Jmeter lib folder, Restart Jmeter
2, Add Thread Group
3, Add JDBC Connection Config, Provide the detail of our DB
4, Add JDBC Request (SELECT statement)
5, Add Listeners
6, Run and validate
```

**Command Line**

https://www.youtube.com/watch?v=K26q5VgwLKk&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=7

```
1, Goto command line -> goto Jmeter -> bin
2, Command
   jmeter -n -t [location of jmeter test script] -l [location of the result file]
   -n -> non gui mode
   -t -> location of jmeter script
   -l -> location of result file
    
    jmeter -h
    jmeter -?
```

