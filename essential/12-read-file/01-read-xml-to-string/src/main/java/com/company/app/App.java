package com.company.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App
{
    public static void main( String[] args ) throws IOException {
//        String path = args[0];
        String path = "data.xml";
        byte[] readAllBytes = Files.readAllBytes(Paths.get(path));
        String xmlContent = new String(readAllBytes);
        System.out.println(xmlContent);
    }
}
/*
output:
<S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/" xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
    <SOAP-ENV:Header/>
    <S:Body>
        <ns2:getText xmlns:ns2="http://soap/">
            <arg0>tom</arg0>
        </ns2:getText>
    </S:Body>
</S:Envelope>
 */
