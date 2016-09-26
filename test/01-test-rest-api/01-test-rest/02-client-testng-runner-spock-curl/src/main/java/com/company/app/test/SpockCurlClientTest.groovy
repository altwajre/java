package com.company.app.test

import groovy.json.JsonSlurper
import org.testng.annotations.Test
import spock.lang.Specification

@Test
class SpockCurlClientTest extends Specification {

    def "curl GET test"() {
        when:
        def response = "curl -X GET http://localhost:8080/hello-world".execute()
        def root = new JsonSlurper().parseText(response.text)
        println root.content

        then:
        root.id > 0
        root.content == 'Hello, Stranger yml!'
    }

}
