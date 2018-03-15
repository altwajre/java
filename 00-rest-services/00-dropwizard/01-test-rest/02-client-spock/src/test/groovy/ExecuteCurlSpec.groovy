import groovy.json.JsonSlurper
import spock.lang.Specification

class ExecuteCurlSpec extends Specification {
    def "Curl - GET test"(){

        when: "execute curl - GET"
        def response = "curl -X GET http://localhost:8080/hello-world".execute()

        then: "verify response"
        def root = new JsonSlurper().parseText(response.text)
        println root.content
        root.id > 0
        root.content == 'Hello, Stranger yml!'

    }
}
