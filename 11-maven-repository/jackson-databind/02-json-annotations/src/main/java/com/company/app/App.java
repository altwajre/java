package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/*
http://websystique.com/java/json/jackson-json-annotations-example/
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.readValue(new File("src/main/resources/data/inputfile.json"), Car.class);
        System.out.println(car);

        final String carJson = mapper.writeValueAsString(car);
        System.out.println(carJson);
    }
}
/*
Car [name=AUDI, model=2014, price=30000, ignoreme1=null, ignoreme2=null, colors=[GRAY, BLACK, WHITE], promoDate=Fri May 09 07:53:20 PDT 2014, otherProperties={tom=jerry, foo=bar}]
{"name":"AUDI","price":"30000","colors":["GRAY","BLACK","WHITE"],"promoDate":"09/53/2014","carModel":2014,"tom":"jerry","foo":"bar"}
 */
