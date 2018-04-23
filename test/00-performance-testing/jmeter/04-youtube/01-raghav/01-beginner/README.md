# Tutorial

https://www.youtube.com/watch?v=M-iAXz8vs48&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c

## Server

Launch Server - App.main()

## Client

> Assertions

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

> Listeners

https://www.youtube.com/watch?v=5FyVKVAqEJo&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=4

```
1, View Results in Table
2, View Results Tree
3, Aggregate Report
4, Graph Results
5, Summary Report
6, Simple Data Writer
```

> Record UI

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

> Database

https://www.youtube.com/watch?v=oy53KAKHpts&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=6

```
1, Add mysql JDBC jar to Jmeter lib folder, Restart Jmeter
2, Add Thread Group
3, Add JDBC Connection Config, Provide the detail of our DB
4, Add JDBC Request (SELECT statement)
5, Add Listeners
6, Run and validate
```

> Command Line

https://www.youtube.com/watch?v=K26q5VgwLKk&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=7

cd apache-jmeter-3.3
sh bin/jmeter -n -t ~/jmeter/GET.jmx -l ~/jmeter/test.csv

```
1, $ cd /Users/whan/Documents/apache-jmeter-3.1/bin
2, Command
   $ ./jmeter.sh -n -t [location of jmeter test script] -l [location of the result file]
   -n -> non gui mode
   -t -> location of jmeter script
   -l -> location of result file

    jmeter -h
    jmeter -?
```

> Web Services API

https://www.youtube.com/watch?v=eaU7951fNuQ&list=PLhW3qG5bs-L-zox1h3eIL7CZh5zJmci4c&index=9

> SOAP

```
1, Test Plan, Thread Group, Soap/XML-RPC Request, View Results Tree
URL: http://...
SOAPAction: ???
Soap/XML-RPC Data:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/">
    <soapenv:Body>
    ...
    </soapenv:Body>
</soapenv:Envelope>
```
