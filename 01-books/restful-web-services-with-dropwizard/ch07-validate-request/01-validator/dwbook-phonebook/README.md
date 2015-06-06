# validate-request-validator - dwbook-phonebook

maven-archetype-quickstart project

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## cURL HTTP requests

### GET request

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`

> `$ curl http://localhost:8080/contact/all`

```
$ curl http://localhost:8080/contact/all
[{"id":1,"firstName":"John","lastName":"Doe","phone":"+123456789"},{"id":2,"firstName":"Jane","lastName":"Doe","phone":"+987654321"}]
```

### POST request

> `$ curl -v -X POST -d '{"firstName": "F", "lastName": "L", "phone": ""}' http://localhost:8080/contact/ --header "Content-Type: application/json"`

```
$ curl -v -X POST -d '{"firstName": "F", "lastName": "L", "phone": ""}' http://localhost:8080/contact/ --header "Content-Type: application/json"
* Hostname was NOT found in DNS cache
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /contact/ HTTP/1.1
> User-Agent: curl/7.37.1
> Host: localhost:8080
> Accept: */*
> Content-Type: application/json
> Content-Length: 48
> 
* upload completely sent off: 48 out of 48 bytes
< HTTP/1.1 400 Bad Request
< Date: Sat, 06 Jun 2015 18:17:57 GMT
< Content-Type: application/json
< Content-Length: 159
< 
* Connection #0 to host localhost left intact
["phone: length must be between 2 and 30","lastName: length must be between 2 and 255","firstName: length must be between 2 and 255","phone: may not be empty"]
```
