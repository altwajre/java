# send back responses server client

## Server

> Launch

Right click `main`, click `run`

> output

Server listening at 8080
/
test string
/file
test file

## Client

> Launch

Right click `main`, click `run`

> output

StatusCode: 200
header content-type: text/html
Response body: Hello from server
StatusCode: 200
Response body: {
  "customer":
  {
    "name": "Tom",
    "age": "28",
    "address": {
      "street": "1 main st",
      "city": "Seattle",
      "state": "WA"
    }
  },
  "car":
  {
    "model": "BMW",
    "color": "Black"
  }
}
