# Customer API

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
