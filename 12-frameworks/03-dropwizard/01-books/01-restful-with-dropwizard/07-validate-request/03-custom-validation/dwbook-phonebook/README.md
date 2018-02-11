# validate-request-custom-validation - dwbook-phonebook

maven-archetype-quickstart project

## Start mysql server

> `mysql.server start`

> `mysql.server stop` when you are done

## Compile

> `mvn package` or `mvn clean install`

## Run the application

> `java -jar target/dwbook-phonebook-1.0-SNAPSHOT.jar server config.yml`

## cURL HTTP requests

### POST request

> `$ curl -v -X POST -d '{"firstName": "Unknown", "lastName": "Unusual", "phone": ""}' http://localhost:8080/contact/ --header "Content-Type: application/json"`

```
$ curl -v -X POST -d '{"firstName": "Unknown", "lastName": "Unusual", "phone": ""}' http://localhost:8080/contact/ --header "Content-Type: application/json"
* Hostname was NOT found in DNS cache
*   Trying ::1...
* Connected to localhost (::1) port 8080 (#0)
> POST /contact/ HTTP/1.1
> User-Agent: curl/7.37.1
> Host: localhost:8080
> Accept: */*
> Content-Type: application/json
> Content-Length: 60
> 
* upload completely sent off: 60 out of 60 bytes
< HTTP/1.1 422 
< Date: Sat, 06 Jun 2015 22:06:12 GMT
< Content-Type: application/json
< Content-Length: 131
< 
* Connection #0 to host localhost left intact
{"errors":["Unknown Unusual is not a valid person","phone length must be between 2 and 30 (was )","phone may not be empty (was )"]}
```

### GET request

> `$ curl -X DELETE http://localhost:8080/contact/3`

> `$ curl -X GET http://localhost:8080/contact/3`

> `$ curl http://localhost:8080/contact/all`

```
$ curl http://localhost:8080/contact/all
[{"id":1,"firstName":"John","lastName":"Doe","phone":"+123456789"},{"id":2,"firstName":"Jane","lastName":"Doe","phone":"+987654321"}]
```
