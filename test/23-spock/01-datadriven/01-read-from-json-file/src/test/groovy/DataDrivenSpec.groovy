import groovy.json.JsonSlurper
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DataDrivenSpec extends Specification {

    def "Read from Json file test - #querystring"() {

        when:
        println querystring
        println querystring.currencyId
        if(querystring.currencyId == null){
            println 'found currencyId=null'
        }
        println querystring.marketId

        then:
        1

        where:
        querystring << new JsonSlurper().parse(new File('src/test/resources/querystring.json')).querystring

    }

}
/*
output:
[currencyId:null, marketId:null]
null
found currencyId=null
null
[currencyId:null, marketId:en-CA]
null
found currencyId=null
en-CA
[currencyId:CAD, marketId:null]
CAD
null
[currencyId:CAD, marketId:en-CA]
CAD
en-CA
 */
