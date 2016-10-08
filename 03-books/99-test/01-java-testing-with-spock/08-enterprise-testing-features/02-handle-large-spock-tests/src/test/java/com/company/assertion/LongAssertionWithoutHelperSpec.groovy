package com.company.assertion

import com.company.Customer
import com.company.Loan
import spock.lang.Specification

// 8.18 Spock test with dubious then: block
// A badly designed then: block. It contains too much assertion code
class LongAssertionWithoutHelperSpec extends Specification {

    def "Normal approval for a loan"(){
        given: "a bank customer"
        Customer customer = new Customer(name: "John Doe", city: "London", address: "10 Bakers", phone: "32434")

        and: "his/her need to buy a house"
        Loan loan = new Loan(years: 5, amount: 200.000)

        when: "a loan is requested"
        customer.requests(loan)

        then: "loan is approved as is"
        loan.approved
        loan.amount == 200.000
        loan.years == 5
        loan.instalments == 60
        loan.getContactDetails().getPhone() == "32434"
        loan.getContactDetails().getAddress() == "10 Bakers"
        loan.getContactDetails().getCity() == "London"
        loan.getContactDetails().getName() == "John Doe"
        customer.activeLoans == 1
    }

}
