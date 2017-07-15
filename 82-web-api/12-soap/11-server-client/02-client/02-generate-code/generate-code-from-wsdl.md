# Generate Code From WSDL

http://localhost:8080/soap/DemoService?wsdl

> under `soap-interface` folder

> run `$ mkdir src run`, `$ wsimport -keep -s src http://localhost:8080/soap/DemoService?wsdl`

> `soap-interface whan$ atom .`

```
<service name="DemoService">
<port name="DemoPort" binding="tns:DemoPortBinding">
<soap:address location="http://localhost:8080/soap/DemoService"/>
</port>
</service>
```

*Intellij - calling soap service*

> create a simple maven project

> create `soap` package, move generated src/*.java into `soap`

> in App.main()

```
public class App
{
    public static void main( String[] args )
    {
        DemoService demoService = new DemoService();
        Demo demoPort = demoService.getDemoPort(); // get the interface
        String tom = demoPort.getText("Tom"); // call service method
        System.out.println(tom);
    }
}
```
