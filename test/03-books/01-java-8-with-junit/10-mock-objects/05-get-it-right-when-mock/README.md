# What’s Important to Get Right When Using Mocks

https://www.safaribooksonline.com/library/view/pragmatic-unit-testing/9781680500769/f_0091.html

- Don't forget that mocks replace real behavior. You want to ask yourself a few questions to make sure you're using
them safely.

- Does your mock really emulate the way the production code works? Does the production code return other formats you're
not thinking of? Does it throw exceptions? Does it return null? You'll want a different test for each these conditions.

- Does your test really use the mock or are you accidentally still triggering production code? In many cases, it's
obvious; in some cases, it's more subtle.

- Finally, remember that you're not testing the production code directly. Any time you introduce a mock, recognize that
you are creating gaps in test coverage. Make sure you have an appropriate higher-level test (perhaps an integration test)
that demonstrates end-to-end use of the real class.
