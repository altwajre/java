# Soap web service

*Example*

https://www.youtube.com/watch?v=_B3rAz8Ge58

## Server

> 01-server/soap

**Start GlassFish Server**

1, Click "Servers" tab

2, Click Start button

3, goto http://localhost:4848/

**Eclipse**

> Right click `soap` project, `Run As`, `Run on Server`, `Finish`, select GlassFish

## Client

> 02-client/01-jersey-client-resources

### postman

http://blog.getpostman.com/wp-content/uploads/2014/08/SOAP-requests-using-Postman.png?7aa172

> POST http://localhost:8080/soap/DemoService?wsdl

> Content-Type text/xml

> Body Raw

```
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:getText xmlns:ns2="http://soap/">
            <arg0>tom</arg0>
        </ns2:getText>
    </S:Body>
</S:Envelope>
```
