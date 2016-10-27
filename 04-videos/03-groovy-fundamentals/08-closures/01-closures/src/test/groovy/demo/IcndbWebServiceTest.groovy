package demo

import groovy.json.JsonSlurper
import org.junit.Test

class IcndbWebServiceTest {

    @Test
    public void icndb_test(){
        String base = 'http://api.icndb.com/jokes/random?'
        def queryString = [limitTo:'[nerdy', firstName: 'Guillaume', lastName:'Laforge']
                .collect {k, v -> "$k=$v"}.join('&')
        println "$base$queryString".toURL().text
    }
    /*
    output:
    { "type": "success", "value": { "id": 412, "joke": "Guillaume Laforge knows the last digit of pi.", "categories": ["nerdy"] } }
     */

    @Test
    public void jsonslurper_icndb_test(){
        String base = 'http://api.icndb.com/jokes/random?'
        def queryString = [limitTo:'[nerdy', firstName: 'Guillaume', lastName:'Laforge']
                .collect {k, v -> "$k=$v"}.join('&')
        def jsonTxt = "$base$queryString".toURL().text
        def json = new JsonSlurper().parseText(jsonTxt)
        println json.value.joke
    }
    /*
    output:
    Guillaume Laforge can solve the Towers of Hanoi in one move.
     */

}
