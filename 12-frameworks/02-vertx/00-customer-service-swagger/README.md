# Customer API

## swagger

http://localhost:8080/api/spec to download

```
---
openapi: "3.0.1"
info:
  title: "Vert.x APIs"
  description: "This specification was generated from a Vert.x Web Router."
servers: []
tags: []
paths:
  /api/customers:
    post:
      parameters: []
  /api/about:
    get:
      parameters: []
```

> Launch App

App.main()

> Test

```
curl -X POST http://localhost:8080/api/customers -H 'content-type: application/json' -d '{"id": "3", "name": "Harry", "age": "38"}'
{
  "id" : 3,
  "name" : "Harry",
  "age" : 38
}

curl http://localhost:8080/api/customers
[ {
  "id" : 1,
  "name" : "Tom",
  "age" : 18
}, {
  "id" : 2,
  "name" : "Dick",
  "age" : 28
}, {
  "id" : 3,
  "name" : "Harry",
  "age" : 38
}
```
