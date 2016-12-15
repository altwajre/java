package com.company;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("resources")
public class StockQuoteApplication extends Application {
}
/*
 * http://localhost:8080/reststock/resources/stock/IBM
 *
 * output:
<stock>
<country>USA</country>
<currency>USD</currency>
<price>43.12</price>
<symbol>IBM</symbol>
</stock>
 */