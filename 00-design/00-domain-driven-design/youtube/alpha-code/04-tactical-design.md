# Tactical Design

https://www.youtube.com/watch?v=WZb-FPmiuMY&index=4&list=PLZBNtT95PIW3BPNYF5pYOi4MJjg_boXCG

> What are Tactical Design Tools?

- Tactical design tools are concerned with implementation details
- Generally takes care of components inside a bounded context
- In fact standard in development world
- You might have heard or used things like services, entities, repositories, factories, they've all been made popular by DDD
- Contrary to strategic design, tactical design is expected to change during product development

## Model Driven Design

-------------------------------------------------------------------
                          Domain

|Subdomain          |  |Subdomain          |  |Subdomain          |
|Bounded Context    |  |Bounded Context    |  |Bounded Context    |
|Domain Model       |  |Domain Model       |  |Domain Model       |
|Ubiquitous Language|  |Ubiquitous Language|  |Ubiquitous Language|
-------------------------------------------------------------------

## Layered Architecture

- Request Handlers
- Controllers
- Business
- Persistence

> Advantages

- You can accept requests faster
- Everything is organized and well defined
- Increases flexibility, maintainability
- Reusable components

## Value Object

https://martinfowler.com/bliki/ValueObject.html
https://dzone.com/articles/value-objects
https://hackernoon.com/value-objects-like-a-pro-f1bfc1548c72
https://www.youtube.com/watch?v=agQt-O_tnF8

Value Object

- Don't care about uniqueness
- Always immutable
- Rich domain logic
- Auto-validating
- Strong equality
- Thread safe

## Entities

https://www.youtube.com/watch?v=3n3OcAIlXjk

- Can be uniquely identified using an ID
- Consists of value objects
- Generally persisted as a row in DB
- Typically mutable
- Generally implements some business logic

## Aggregates

- When the object graph becomes big, it becomes difficult to maintain
- An aggregate is collection of entities and values which comes under a single transaction boundary
- An aggregate controls the change
- An aggregate always has a root entity
- The root entity governs the lifetime of other entities in the aggregate
- An aggregate is always consistent
- Domain events are generated to ensure eventual consistency

----------------------------
[Customer] -> [CustomerInfo]
    |
    v
[Address]
----------------------------

----------------------------
[Order] -> [OrderInfo]
    |
    v
[OrderItem]
----------------------------

## Factories and Repositories

Use Factories and Repositories to handle aggregates

- Factories helps you to create new aggregates
- Repository helps you to get persisted aggregates
- Repository != DAO (fetch the raw entity)
