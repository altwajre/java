# A Testing Challenge

https://www.safaribooksonline.com/library/view/pragmatic-unit-testing/9781680500769/f_0085.html

Testing trouble - any tests we could write against the AddressRetriever.retrieve() will end up executing a live HTTP 
call, which would carry at least two significant implications

- The tests against the live call will be slow in comparison to the bulk of our other, fast tests.
- We can't guarantee that the HTTP API will always be available and return consistent results. It's out of our control.

> Primary goal

We want to unit-test the logic in retrieve() in isolation from any other code or dependencies. Given that we trust the
HttpImpl class, what remains to test is the logic that prepares the HTTP call and the logic that populates an Address
given the HTTP response.
