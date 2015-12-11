# 02-add-feature

## README.md - previous steps

> `01-create-feature/01-no-features-found/README.md`

> `01-create-feature/02-add-feature/README.md`

## Create step definitions

> `mkdir step_definitions`

> add `features/checkout.feature`

> add `step_definitions/CheckoutSteps.java`

> update `cucumber`

## Run Test

> `./cucumber`

```
Output:
Feature: Checkout
  Scenario: Checkout a banana              # checkout.feature:2
    Given the price of a "banana" is 40c   # CheckoutSteps.thePriceOfAIsC(String,int)
      cucumber.api.PendingException: TODO: implement me
      	at step_definitions.CheckoutSteps.thePriceOfAIsC(CheckoutSteps.java:18)
      	at ✽.Given the price of a "banana" is 40c(checkout.feature:3)      
    When I checkout 1 "banana"             # CheckoutSteps.iCheckout(int,String)
    Then the total price should be 40ccode
1 Scenarios (1 undefined)
3 Steps (1 skipped, 1 pending, 1 undefined)
0m0.077s
cucumber.api.PendingException: TODO: implement me
	at step_definitions.CheckoutSteps.thePriceOfAIsC(CheckoutSteps.java:18)
	at ✽.Given the price of a "banana" is 40c(checkout.feature:3)
You can implement missing steps with the snippets below:
@Then("^the total price should be (\\d+)ccode$")
public void theTotalPriceShouldBeCcode(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```
