# Web Api in-memory

## Run the App

### Intellij

#### Server

Launch App with Program args: server

#### Client - cURL

**GET All**

> `$ curl http://localhost:8080/contacts`

```
[{"id":0,"name":"Tom"},{"id":1,"name":"Dick"},{"id":2,"name":"Harry"}]
```

**GET**

> `$ curl http://localhost:8080/contacts/2`

```
{"id":1,"name":"Dick"}
```

**POST**

> `$ curl -X POST -H "Content-Type: application/json" -d '{"id": 4, "name": "Will"}' "http://localhost:8080/contacts"`

**PUT**

> `curl -X PUT -H "Content-Type: application/json" -d '{"id": 1, "name": "Will"}' "http://localhost:8080/contacts/1"`

```
{"id":0,"name":"Will"}
```

**DELETE**

> `curl -X DELETE "http://localhost:8080/contacts/4"`
