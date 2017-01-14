# jersey client file

## Compile

> `mvn package`

## Run

> `$ java -jar target/app-1.0-SNAPSHOT.jar data.xml`

output:

```
InboundJaxrsResponse{context=ClientResponse{method=POST, uri=http://localhost:8080/soap/DemoService?wsdl, status=200, reason=OK}}
<?xml version='1.0' encoding='UTF-8'?><S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"><S:Body><ns2:getTextResponse xmlns:ns2="http://soap/"><return>Hello tom from soap</return></ns2:getTextResponse></S:Body></S:Envelope>
```
