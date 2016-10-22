package observables

import rx.Observable
import spock.lang.Specification

class ObservableSpec extends Specification {

    def customObservableBlocking() {
        return Observable.create { s ->
            50.times { i ->
                if (!s.unsubscribed) {
                    s.onNext("value_${i}")
                }
            }
            if (!s.unsubscribed) {
                s.onCompleted()
            }
        }
    }

    def "observable test"() {
        when:
        customObservableBlocking().subscribe{println(it)}

        then:
        1

    }

    def "times test"(){
        when:
        5.times { i -> println i}

        then:
        1
    }

}
