# Concepts

https://gatling.io/docs/current/general/concepts/

## Virtual User

- DSL (Domain Specific Language) - fast writing of scenarios and easy maintenance of existing scenarios
- Ability to deal with logic between requests
- Gatling implements virtual users as messages instead of threads which scales much better

## Simulation

A simulation is a description of the load test. It describes how, possibly several, user populations will run: which scenario they will execute and how new virtual users will be injected.

> Injection - Simulation setup

https://gatling.io/docs/current/general/simulation_setup/#simulation-setup

> Assertions

https://gatling.io/docs/current/general/assertions/#assertions

> HTTP Protocol

https://gatling.io/docs/current/http/http_protocol/#http-protocol

## Session

Each virtual user is backed by a Session. Those sessions are the actual messages that go down the scenario workflow. A Session is basically a state placeholder, where testers can inject or capture and store data.
