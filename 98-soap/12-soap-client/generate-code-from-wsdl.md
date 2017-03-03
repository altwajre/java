# Generate Code From WSDL

## wsimport

**youtube**

https://www.youtube.com/watch?v=6hqDMS-oJ9k

## 01-geoipservice project

*generate code from wsdl*

> under `soap-interface` folder

> run `$ mkdir src run`, `$ wsimport -keep -s src http://www.webservicex.net/geoipservice.asmx?WSDL` - keep src

> run `$ wsimport http://www.webservicex.net/geoipservice.asmx?WSDL`

> `soap-interface whan$ atom .`

```
<?xml version="1.0" encoding="UTF-8"?>
<wsdl:service name="GeoIPService">
  <wsdl:port name="GeoIPServiceSoap" binding="tns:GeoIPServiceSoap">
    <soap:address location="http://www.webservicex.net/geoipservice.asmx"/>
  </wsdl:port>
  <wsdl:port name="GeoIPServiceSoap12" binding="tns:GeoIPServiceSoap12">
    <soap12:address location="http://www.webservicex.net/geoipservice.asmx"/>
  </wsdl:port>
  <wsdl:port name="GeoIPServiceHttpGet" binding="tns:GeoIPServiceHttpGet">
    <http:address location="http://www.webservicex.net/geoipservice.asmx"/>
  </wsdl:port>
  <wsdl:port name="GeoIPServiceHttpPost" binding="tns:GeoIPServiceHttpPost">
    <http:address location="http://www.webservicex.net/geoipservice.asmx"/>
  </wsdl:port>
</wsdl:service>
```

*Intellij - calling soap service*

> create a simple maven project

> create `net.webservicex` package, move generated src/*.java into `net.webservicex`

> in App.main()

```
public class App
{
    public static void main( String[] args )
    {
        String ipAddress = "172.217.5.78"; // $ ping google.com
        GeoIPService ipService = new GeoIPService();
        GeoIPServiceSoap geoIPServiceSoap = ipService.getGeoIPServiceSoap();
        GeoIP geoIP = geoIPServiceSoap.getGeoIP(ipAddress);
        System.out.println(geoIP.getCountryName());
    }
}
```
