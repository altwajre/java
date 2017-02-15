package com.company.assertion

import com.company.Customer
import com.company.Loan
import spock.lang.Specification

// 8.20 Using helper methods for assertions
class HelperAssertionSpec extends Specification {

    def "Normal approval for a loan - improved"() {
        given: "a bank customer"
        Customer customer = new Customer(name: "John Doe", city: "London", address: "10 Bakers", phone: "32434")

        and: "his/her need to buy a house"
        int sampleTimeSpan=5
        int sampleAmount = 200.000
        Loan loan = new Loan(years:sampleTimeSpan, amount:sampleAmount)

        when:"a loan is requested"
        customer.requests(loan)

        then: "loan is approved as is"
        loanApprovedAsRequested(customer, loan, sampleTimeSpan, sampleAmount)

        and: "contact details are kept or record"
        contactDetailsMatchCustomer(customer, loan)
    }

    private void loanApprovedAsRequested(Customer customer, Loan loan, int originalYears, int originalAmount){

        // with() method works as expected in helper method
        with(loan){
            approved
            amount == originalAmount
            loan.years == originalYears
            loan.instalments == originalYears * 12
        }
        // assert keyword is needed in helper method
        assert customer.activeLoans == 1

    }

    private void contactDetailsMatchCustomer(Customer customer, Loan loan){

        // Clear connection between loan and customer who requested it
        with(loan.contactDetails){
            phone == customer.phone
            address == customer.address
            city == customer.city
            name == customer.name
        }
    }
}
