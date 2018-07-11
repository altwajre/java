# Testing Microservices

https://www.infoq.com/presentations/testing-microservices

---

> Developer

```
Test Driven Development
Consumer Driven Contract Tests
Cross System Tests
Unit Performance Testing
```

> Tester

```
Identify Risks
Exploratory Testing
Swarming
Performance Testing
Deployment Testing
```

---

Microservices are loosely coupled that can be deployed independently.

---

> Unit Tests

exercise the smallest pieces of testable software in the application to determine whether they behave as expected.

> Integration Tests

verify the communication paths and interactions between components to detect interface defects.

> Component Tests

limit the scope of the exercised software to a portion of the system under test manipulating the system
through internal code interfaces and using test doubles to isolate the code under test from other components.

> Contract Tests

verify interactions at the boundary of an external service asserting that it meets the contract expected by a consuming service.

> End-to-end Tests

verify that a system meets external requirements and achieves its goals, testing the entire system, from end to end.

---

> Complex

the relationship between cause and effect can only be perceived in retrospect

probe - sense - respond

emergent practice

> Chaotic

no relationship between cause and effect at systems level

act - sense - respond

novel practice

> Complicated

the relationship between cause and effect requires analysis or some other form of investigation and/or the application of expert knowledge

sense - analyze - respond

good practice

> Simple

the relationship between cause and effect is obvious to all

sense - categorize - respond

best practice

---

> Risk Based Testing

- Risk profiles change over time
- Risk changes depending on the complexity of the system
- Risk changes depending on how much knowing your intended outcome
- Risk profiles change depending on how much information you have
- Risk changes depending on who wants the information

---

> Focus on Risks

- Key Risks: Security, Performance, Reliability
- Risk through the numbers of systems and complexity
- Risk of testing in production

> Diversify Test Environments

- Diversity of Test Environments
 - Local Machines for Fast Feedback
 - Testing Environment in a Box
 - Performance Testing Environments
 - Work in Progress (Testing in Production)

> Diversify Test Data

- Different ways to load data (built in house tools)
- Different datasets to load up
- Diversity in test data (different states)

> Diversify Tester Skillset

- Testers who support test environments
- Testers who focus on understanding the business and how they operate
- Testers who focus on working with developers

> Summary

- Both Checking & Exploration are vital
- Use Risk to help you decide what to test
- Swarming can be really useful to target risk
- Diversify your strategy
- Treat e2e automation with kit gloves
- Mimic Production where useful
