# Bounded Context and Context Map

https://www.youtube.com/watch?v=Evers5npkmE

## Bounded Context

https://martinfowler.com/bliki/BoundedContext.html

Bounded Context is a central pattern in Domain-Driven Design. It is the focus of DDD's strategic design section which is all about dealing with large models and teams. DDD deals with large models by dividing them into different Bounded Contexts and being explicit about their interrelationships.

### Example: Customer inside of Bounded Context

Each Bounded Context has different take for the customer
In each Bounded Context, customer has different meaning
Each Bounded Context has their own Customer model & Ubiquitous Language

> Sales

Customer = Social Interests, likes, needs

> Support

Customer = History, Tickets

> Accounting

Customer = Method of Payment

> Order

Customer = Addresses, Availability

## Context Map

https://www.infoq.com/articles/ddd-contextmapping
https://www.youtube.com/watch?v=vwr2wAclIwg - understanding context maps

- Context Map is drawing Bounded Contexts with lines to map a fuzzy situation

- Context Map is designated as the primary tool used to make context boundaries explicit
