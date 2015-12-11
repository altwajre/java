# 05-test-checkout-class

## README.md - previous steps

> `01-create-feature/01-no-features-found/README.md`

> `01-create-feature/02-add-feature/README.md`

> `02-create-step-definitions/README.md`

> `03-implement-step-definition/README.md`

> `04-change-output/README.md`

## Add implementation

> `implementation/Checkout.java`

## Update Test

> `step_definitions/CheckoutSteps.java` - `CheckoutSteps.iCheckout()`

## Run Test

> `./cucumber` - 2 passed

```
Output:
..U
1 Scenarios (1 undefined)
3 Steps (1 undefined, 2 passed)
0m0.085s
You can implement missing steps with the snippets below:
@Then("^the total price should be (\\d+)ccode$")
public void theTotalPriceShouldBeCcode(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```
