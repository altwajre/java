package com.company.assertion

import com.company.Customer
import com.company.Loan
import spock.lang.Specification

// 8.19 Improved Spock test with clear separation of checks
class GroupAssertionsSpec extends Specification {

    def "Normal approval for a loan" () {
        given: "a bank customer"
        Customer customer = new Customer(name: "John Doe", city: "London", address: "10 Bakers", phone: "32434")

        and: "his/her need to buy a house"
        // Makes clear the connection between expected results
        int sampleTimeSpan = 5
        int sampleAmount = 200.000
        Loan loan = new Loan(years: sampleTimeSpan, amount: sampleAmount)

        when: "a loan is requested"
        customer.requests(loan)

        then: "loan is approved as is"

        // Grouping of primary loan checks
        with(loan){
            approved
            // Makes clear the expected result
            amount == sampleAmount
            years == sampleTimeSpan
            instalments == sampleTimeSpan * 12
        }
        customer.activeLoans == 1

        // Different block for secondary checks
        and: "contact details are kept or record"
        with(loan.contactDetails){
            getPhone() == "32434"
            getAddress() == "10 Bakers"
            getCity() == "London"
            getName() == "John Doe"
        }
    }
}
