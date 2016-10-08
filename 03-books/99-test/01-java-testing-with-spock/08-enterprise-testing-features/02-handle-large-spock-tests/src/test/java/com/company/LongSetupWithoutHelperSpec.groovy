package com.company

import spock.lang.Specification

// 8.15 A Spock test with long setup - don't do this
// A badly designed and: block. It contains too much code
class LongSetupWithoutHelperSpec extends Specification {
    def "a bank customer with 3 credit cards is never given a loan"(){

        given: "a customer that wants to get a loan"
        Customer customer = new Customer(name: "John Doe")

        and: "his credit cards"

        BankAccount account1 = new BankAccount()
        account1.with {
            setNumber("234234")
            setHolder("John doe")
            balance = 30
        }
        CreditCard card1 = new CreditCard("447978956666")
        card1.with {
            setHolder("John Doe")
            assign(account1)
        }
        customer.owns(card1)

        BankAccount account2 = new BankAccount()
        account2.with{
            setNumber("3435676")
            setHolder("John Doe")
            balance=30
        }
        CreditCard card2 = new CreditCard("4443543354")
        card2.with{
            setHolder("John Doe")
            assign(account2)
        }
        customer.owns(card2)

        BankAccount account3 = new BankAccount()
        account2.with{
            setNumber("45465")
            setHolder("John Doe")
            balance=30
        }
        CreditCard card3 = new CreditCard("444455556666")
        card3.with{
            setHolder("John Doe")
            assign(account3)
        }
        customer.owns(card3)

        when: "a loan is requested"
        Loan loan = new Loan()
        customer.requests(loan)

        then: "loan should not be approved"
        !loan.approved
    }
}
