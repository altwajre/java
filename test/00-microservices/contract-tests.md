# Contract Testing

Microservices are loosely coupled that can be deployed independently.

I am in favor of automating as much as possible because we need to validate the software quickly and efficiently.

Contract tests verify interactions at the boundary of an external service asserting that it meets the contract expected by a consuming service.

## Test Pyramid

Increasing scope
More confidence
   ^                    [ UI ]            |
   |                 [  Service  ]        |
   |               [      Unit      ]     V
                                        Faster!
                                    Better isolation

> Trade-Offs

*go up the pyramid*

- take long time to run, get slow feedback cycle
- hard to determine which functionality has broken when test failed

*go down the pyramid*

- tests run faster, get faster feedback cycles
- know what broke when test failed
- don't get confidence that our system as a whole works

## Consumer-Driven Tests

consumer-driven contract (CDC) as service tests get faster feedback cycles

- Defining the expectations of a consumer on a service (or producer). The expectations of the consumers are captured in code form as testes, which are then run against the service (producer).
- If done right, CDCs should be run as part of the CI build of the producer to ensure never deploy changes that breaks one of these contracts.
- these tests need to be run only against a single producer in isolation because it is faster and more reliable than end-to-end tests

> For example, the customer service has two separate consumers (Helpdesk and Web shop)

[ Helpdesk ] \
               [ Customer service ] -> [ Stub database ]
[ Web shop ] /

- create two sets of tests: one for each consumer representing the helpdesk's and web-shop's use of the customer service.
- have someone from the producer and consumer teams collaborate on creating the tests.

> Pact

Pact is a contract testing tool.

https://docs.pact.io/
