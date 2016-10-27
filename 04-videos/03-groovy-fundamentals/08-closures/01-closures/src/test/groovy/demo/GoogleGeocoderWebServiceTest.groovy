package demo

import org.junit.Test

class GoogleGeocoderWebServiceTest {

    @Test
    public void tourl_test(){
        println 'http://oreilly.com'.toURL().text // get web page text
    }

    @Test
    public void google_geocode_test(){
        String base = 'https://maps.googleapis.com/maps/api/geocode/xml?'
        def encoded = ['10 Fawcett Street', 'Cambridge', 'MA'].collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        def queryString = "address=$encoded"
        println queryString
        println "$base$queryString".toURL().text
    }
    /*
    output:
address=10+Fawcett+Street,Cambridge,MA
<?xml version="1.0" encoding="UTF-8"?>
<GeocodeResponse>
 <status>OK</status>
 <result>
 ...
  <geometry>
   <location>
    <lat>42.3893467</lat>
    <lng>-71.1455120</lng>
   </location>
 ...
 </result>
</GeocodeResponse>
     */

    @Test
    public void xmlslurper_google_geocode_test(){
        String base = 'https://maps.googleapis.com/maps/api/geocode/xml?'
        def encoded = ['10 Fawcett Street', 'Cambridge', 'MA'].collect {
            URLEncoder.encode(it, 'UTF-8')
        }.join(',')
        def queryString = "address=$encoded"
        println queryString
        def root = new XmlSlurper().parse("$base$queryString")
        def loc = root.result[0].geometry.location
        println "(${loc.lat},${loc.lng})"
    }
    /*
    output:
    address=10+Fawcett+Street,Cambridge,MA
    (42.3893467,-71.1455120)
     */

}
