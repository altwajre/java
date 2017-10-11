# Building Microservices (distributed system) Testing

https://www.safaribooksonline.com/library/view/building-microservices/9781491950340/ch07.html

## Types of Tests

I am in favor of automating as much as possible because we need to validate the software quickly and efficiently.

> Business facing

help the nontechnical stakeholders understand how the system works

- Acceptance testing (UAT env)
Did we build the right thing? Automated

- Exploratory testing
Usability; how can I break the system? Manual

> Technology facing

- Unit testing
Did we build it right? Automated (xUnit framework)

- Property testing
Response time; scalability; performance; security Tools

## Test Scope

> Test Pyramid

Increasing scope
More confidence
   ^                    [ UI ]            |
   |                 [  Service  ]        |
   |               [      Unit      ]     V
                                        Faster!
                                    Better isolation

> Application

[ UI ] -> [ Service ] -> [ Database ]

> Unit Tests

test scope - ()

[ UI ] -> [ Service (unit test service code) ] -> [ Database ]

- test-driven-design (TDD)
- mock
- very fast feedback, run thousands of tests in less than a minute.
- catch most of bugs
- support refactoring of code

> Service Tests

test scope - ()

[ UI (skip) ] -> ([ Service ] test service endpoints directly) -> [ (stub) Database ]

- bypass the user interface and test services directly
- test each service's capabilities to improve the isolation to make finding and fixing problems faster
- stub out all external collaborators so only the service itself is in scope

> End-to-End Tests

test scope - ()

([ UI ] -> [ Service ] -> [ Database ] end-to-end scope)

- run against the entire system
- have a high confidence that the code being tested will work in production
- tricky to do well in a microservices context
x
> Trade-Offs

*go up the pyramid*

- take long time to run, get slow feedback cycle
- hard to determine which functionality has broken when test failed

*go down the pyramid*

- tests run faster, get faster feedback cycles
- know what broke when test failed
- don't get confidence that our system as a whole works

when end-to-end tests fail, we will try to write a fast unit test to catch that problem in the future to improve the feedback cycles.

## Implementing Service Tests

> Mocking and Stubbing

- stubbing downstream collaborators

## Those Tricky End-to-End Tests

test against the helpdesk and web-shop front-end apps

[ Build ] -> [ Unit tests ] -> [ Service tests ] -> [ End-to-end tests ]

> Questions

- which version of the other services should we use? should we run tests against the version of helpdesk and web-shop that are in production? But what if there is a new version of either the helpdesk or web-shop queued up to go live?

> service tests and end-to-end tests are testing the same thing, may duplicate lots of effort to deploy all those services in the first place.

*Solution*

having multiple pipelines fan in to a single, end-to-end test stage.
whenever a new build of one of our services is triggered, we run the end-to-end tests.

```
Web shop            [ Build ] -> [ Unit tests ] -> [ Service tests ] \
Customer service    [ Build ] -> [ Unit tests ] -> [ Service tests ] -> [ End-to-end tests ]
Helpdesk            [ Build ] -> [ Unit tests ] -> [ Service tests ] /
```

## Flaky and Brittle Tests

https://martinfowler.com/articles/nonDeterminism.html

Delete not reliable (flaky) tests to keep test results reliable and stable because failing tests are NOT ok.
Flaky tests fail, they don't tell much. We re-run them in the hope that they will pass.

## Test Journeys, Not Stories

> if we add new end-to-end test for every piece of functionality we add

- many users end-to-end tests can still be manageable with one or two services
- test suites become too big (unmanageable) when there are 10 or 20 services

The best way to counter this is to focus on a small number of core journeys to test for the whole system.

## Consumer-Driven Tests to the Rescue

consumer-driven contract (CDC) as service tests instead of end-to-end tests

- Defining the expectations of a consumer on a service (or producer). The expectations of the consumers are captured in code form as testes, which are then run against the service (producer).
- If done right, CDCs should be run as part of the CI build of the producer to ensure never deploy changes that breaks one of these contracts.
- these tests need to be run only against a single producer in isolation because it is faster and more reliable than end-to-end tests

> The customer service has two separate consumers (Helpdesk and Web shop)

[ Helpdesk ] \
               [ Customer service ] -> [ Stub database]
[ Web shop ] /

- create two sets of tests: one for each consumer representing the helpdesk's and web-shop's use of the customer service.
- have someone from the producer and consumer teams collaborate on creating the tests.

> Pact

Pact is a consumer-driven testing tool.

Contract Testing

https://docs.pact.io/

## Should We Use End-to-End Tests?

- end-to-end tests have a large number of disadvantages that grow significantly as you add more moving part under test.
- many teams have been implementing microservices at large scale have learned that over time remove the need entirely for end-to-end tests. They will use CDCs instead.
- people would rather work as hard as they can to eliminate any defects before production, even if that means software takes longer to ship.
