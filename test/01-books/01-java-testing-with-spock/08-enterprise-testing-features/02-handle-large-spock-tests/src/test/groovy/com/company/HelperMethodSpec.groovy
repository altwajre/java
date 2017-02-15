package com.company

import spock.lang.Specification

// 8.16 Spock test with helper methods
class HelperMethodSpec extends Specification {

    def "a bank customer with 3 credit cards is never given a loan"() {

        given: "a customer that wants to get a loan"
        String customerName = "John Doe"
        Customer customer = new Customer(name: customerName)

        and: "his credit cards"
        // Setup code is now short and clean
        customer.owns(createCreditCard("447978956666", customerName))
        customer.owns(createCreditCard("4443543354", customerName))
        customer.owns(createCreditCard("444455556666", customerName))

        when: "a loan is requested"
        Loan loan = new Loan()
        customer.requests(loan)

        then: "loan should not be approved"
        !loan.approved

        when:
        def x = true

        then:
        x

    }

    // 8.17 Using arguments that imply their importance in the test
    def "a bank customer with 3 credit cards is never given a loan -alt 2"() {

        given: "a customer that wants to get a loan"
        String customerName ="doesNotMatter"
        Customer customer = new Customer(name:customerName)

        and: "his credit cards"
        customer.owns(createCreditCard("anything",customerName))
        customer.owns(createCreditCard("whatever",customerName))
        customer.owns(createCreditCard("notImportant",customerName))

        expect: "customer already has 3 cards"
        customer.getCards().size() == 3

        when:"a loan is requested"
        Loan loan = new Loan()
        customer.requests(loan)

        then: "therefore loan is not approved"
        !loan.approved

    }

    // A helper method that deals with credit card
    private CreditCard createCreditCard(String number, String holder){

        // The fact that each credit card needs a bank account is hidden in the helper method
        BankAccount account = new BankAccount()
        account.with {
            setNumber("45465")
            setHolder(holder)
            balance = 30
        }

        CreditCard card = new CreditCard(number)
        card.with{
            setHolder(holder)
            assign(account)
        }
        return card
    }

}
