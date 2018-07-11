# 04-change-output

## README.md - previous steps

> `01-create-feature/01-no-features-found/README.md`

> `01-create-feature/02-add-feature/README.md`

> `02-create-step-definitions/README.md`

> `03-implement-step-definition/README.md`

## Change Output

### Update cucumber

> `-p progress` - progress output

```
. = passed
P = pending
U = undefined
```

## Run Test

> `./cucumber` - 1 passed

```
Output:
.PU
1 Scenarios (1 undefined)
3 Steps (1 pending, 1 undefined, 1 passed)
0m0.073s
cucumber.api.PendingException: TODO: implement me
	at step_definitions.CheckoutSteps.iCheckout(CheckoutSteps.java:23)
	at ✽.When I checkout 1 "banana"(checkout.feature:4)
You can implement missing steps with the snippets below:
@Then("^the total price should be (\\d+)ccode$")
public void theTotalPriceShouldBeCcode(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```
