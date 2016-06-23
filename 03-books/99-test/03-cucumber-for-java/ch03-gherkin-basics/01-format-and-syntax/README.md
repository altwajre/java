# gherkin format-and-syntax

## download cucumber jars

> download following jars from the public Maven repository at http://search.maven.org/

```
cucumber-core
cucumber-java
cucumber-jvm-deps
gherkin
```

## Run Test

> `​java -cp ".:jars/*" cucumber.api.cli.Main -g step_definitions --dry-run features​` - `-cp` is `classpath`

```
Output: 
UUUUUUUUUUUUUU
2 Scenarios (2 undefined)
14 Steps (14 undefined)
0m0.000s
You can implement missing steps with the snippets below:
@Given("^I have chosen some items to buy$")
public void i_have_chosen_some_items_to_buy() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Given("^I am about to enter my credit card details$")
public void i_am_about_to_enter_my_credit_card_details() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^I enter a card number that's only (\\d+) digits long$")
public void i_enter_a_card_number_that_s_only_digits_long(int arg1) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^all the other details are correct$")
public void all_the_other_details_are_correct() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^I submit the form$")
public void i_submit_the_form() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Then("^the form should be redisplayed$")
public void the_form_should_be_redisplayed() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Then("^I should see a message advising me of the correct number of digits$")
public void i_should_see_a_message_advising_me_of_the_correct_number_of_digits() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@When("^I enter a card expiry date that's in the past$")
public void i_enter_a_card_expiry_date_that_s_in_the_past() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
@Then("^I should see a message telling me the expiry date must be wrong$")
public void i_should_see_a_message_telling_me_the_expiry_date_must_be_wrong() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
}
```

## Shell script

### Create shell script

> create `cucumber` at project root

> make cucumber file executable `chmod u+x cucumber`

### Run shell script

> `./cucumber` at project root

