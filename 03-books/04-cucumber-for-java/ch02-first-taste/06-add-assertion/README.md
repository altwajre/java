# 06-add-assertion

## README.md - previous steps

> `01-create-feature/01-no-features-found/README.md`

> `01-create-feature/02-add-feature/README.md`

> `02-create-step-definitions/README.md`

> `03-implement-step-definition/README.md`

> `04-change-output/README.md`

> `05-test-checkout-class/README.md`

## Add implementation

> `implementation/Checkout.java`

## Update Test

> `step_definitions/CheckoutSteps.java` - `CheckoutSteps.theTotalPriceShouldBeC()`

## Run Test

> `./cucumber` - 2 passed, 1 Failed

```
Output:
..F
Failed scenarios:
checkout.feature:2 # Scenario: Checkout a banana
1 Scenarios (1 failed)
3 Steps (1 failed, 2 passed)
0m0.081s
java.lang.AssertionError: expected:<40> but was:<0>
	at org.junit.Assert.fail(Assert.java:92)
	at org.junit.Assert.failNotEquals(Assert.java:646)
	at org.junit.Assert.assertEquals(Assert.java:127)
	at org.junit.Assert.assertEquals(Assert.java:471)
	at org.junit.Assert.assertEquals(Assert.java:455)
	at step_definitions.CheckoutSteps.theTotalPriceShouldBeC(CheckoutSteps.java:25)
	at ✽.Then the total price should be 40c(checkout.feature:5)
```
