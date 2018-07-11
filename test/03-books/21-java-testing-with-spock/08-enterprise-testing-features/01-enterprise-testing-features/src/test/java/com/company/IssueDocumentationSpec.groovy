package com.company

import spock.lang.Issue
import spock.lang.Specification

class IssueDocumentationSpec extends Specification {

    // 8.4 Marking a test method with the issue it solves
    // This test method verifies the fix that happened for JIRA issue 561
    @Issue("JIRA-561")
    def "Error conditions for unknown products"(){

        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        when: "warehouse is queried for the wrong product"
        inventory.isProductAvailable("productThatDoesNotExist",1)

        then: "an exception should be thrown"
        thrown(IllegalArgumentException)

    }

    // 8.5 Using the URL of an issue solved by a Spock test
    // This test method verifies the fix that happened for Redmine issue 2554
    @Issue("http://redmine.example.com/issues/2554")
    def "Error conditions for unknown products - better"() {
        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        when: "warehouse is queried for the wrong product"
        inventory.isProductAvailable("productThatDoesNotExist",1)

        then: "an exception should be thrown"
        IllegalArgumentException e = thrown()
        e.getMessage() == "Uknown product productThatDoesNotExist"
    }

    // 8.6 Marking a Spock test with multiple issues
    // This test method verifies the fix for three duplicate bugs.
    @Issue(["JIRA-453","JIRA-678","JIRA-3485"])
    def "Negative quantity is the same as 0"() {
        given: "a warehouse"
        WarehouseInventory inventory = new WarehouseInventory()

        and: "a product"
        Product tv = new Product(name:"bravia",price:1200,weight:18)

        when: "warehouse is loaded with a negative value"
        inventory.preload(tv,-5)

        then: "the stock is empty for that product"
        notThrown(IllegalArgumentException)
        !inventory.isProductAvailable(tv.getName(),1)
    }
}
