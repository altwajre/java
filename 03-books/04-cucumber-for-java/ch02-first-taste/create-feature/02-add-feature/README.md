# 02-add-feature

## 01-no-features-found/README.md

## Add feature

> add `features/checkout.feature`

## Run Test

> `./cucumber`

```
Output: by default step definitions use snake case
Feature: Checkout
  Scenario: Checkout a banana              # features/checkout.feature:2
    Given the price of a "banana" is 40c
    When I checkout 1 "banana"
    Then the total price should be 40ccode
1 Scenarios (1 undefined)
3 Steps (3 undefined)
0m0.000s
You can implement missing steps with the snippets below:
@Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
public void the_price_of_a_is_c(String arg1, int arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^I checkout (\\d+) \"([^\"]*)\"$")
public void i_checkout(int arg1, String arg2) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Then("^the total price should be (\\d+)ccode$")
public void the_total_price_should_be_ccode(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```

### camel case step definitions

> `java -cp "jars/*" cucumber.api.cli.Main -p pretty --snippets camelcase features`

```
Output:
Feature: Checkout
  Scenario: Checkout a banana              # checkout.feature:2
    Given the price of a "banana" is 40c
    When I checkout 1 "banana"
    Then the total price should be 40ccode
1 Scenarios (1 undefined)
3 Steps (3 undefined)
0m0.000s
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
@Then("^the total price should be (\\d+)ccode$")
public void theTotalPriceShouldBeCcode(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```

