/*
 * This Spock specification was auto generated by running the Gradle 'init' task
 * by 'whan' at '3/19/16 6:49 PM' with Gradle 2.12
 *
 * @author whan, @date 3/19/16 6:49 PM
 */

import spock.lang.Specification

class LibraryTest extends Specification{
    def "someLibraryMethod returns true"() {
        setup:
        Library lib = new Library()
        when:
        def result = lib.someLibraryMethod()
        then:
        result == true
    }
}
