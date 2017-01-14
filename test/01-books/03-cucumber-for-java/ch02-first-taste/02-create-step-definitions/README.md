# 02-create-step-definitions

## README.md - previous steps

> `01-create-feature/01-no-features-found/README.md`

> `01-create-feature/02-add-feature/README.md`

## Create step definitions

> `mkdir step_definitions`

> `./cucumber` - generate the camelcase code snippets

```
You can implement missing steps with the snippets below:
@Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
public void thePriceOfAIsC(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^I checkout (\\d+) \"([^\"]*)\"$")
public void iCheckout(int arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Then("^the total price should be (\\d+)c$")
public void theTotalPriceShouldBeC(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```

> add the camelcase code snippets to `step_definitions/CheckoutSteps.java`

> update `cucumber` - `java -cp "jars/*:."`

```
mac and linux
1, : is separator
2, added the current directory "." to the classpath
3, added -g step_definitions to tell Cucumber the step definitions that will "glue" the steps in feature file 
```

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
