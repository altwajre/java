package com.company

import groovyx.net.http.RESTClient
import org.junit.runners.model.MultipleFailureException
import spock.lang.Shared
import spock.lang.Specification

class RetrySpec extends Specification {

    @Shared
    def client = new RESTClient("http://localhost:8080/")

    def "Retry Spec"(){

        when: ""
        def response = client.get(path: "topics")

        then:
        with(response){
            print data
            status == 200
        }
    }

    def retry(int times = 3, Closure body){
        int retries = 0
        def exceptions = []
        while (retries++ < times){
            try{
                return body.call()
            }
            catch (e){
                exceptions << e
            }

        }

        throw new MultipleFailureException("Failed after $times retries", exceptions)
    }

}
