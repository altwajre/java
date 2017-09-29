package com.company.app;

import net.webservicex.GeoIP;
import net.webservicex.GeoIPService;
import net.webservicex.GeoIPServiceSoap;

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
