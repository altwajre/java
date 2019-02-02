# Object Design Examples with GRASP

https://learning.oreilly.com/library/view/applying-uml-and/0131489062/ch18.html

`GRASP patterns (Creator, Information Expert, Low Coupling, Controller, High Cohesion) by name are not important`

# Key Point

`The assignment of responsibilities and design of collaborations are very important and creative steps during design, both while diagraming and while coding`

# Use Case Realizations for the NextGen Iteration

## Initialization and the Start Up Use Case

The `Start Up` use case realization is the design context in which to consider creating most of the `root` or long-lived objects.

### Guideline

When coding, program at least some Start Up Initialization first. But during OO design modeling, consider the Start Up initialization design last, after you have discovered what really needs to be created and initialized. Then, design the initialization to support the needs of other use case realizations.

# Use Case Realizations for the NextGen Iteration

## Creating A New Sale

GRASP Creator pattern suggests assigning the responsibility for creation to a class that aggregates, contains, or `records` the object to be created.

Analyzing the Domain Model reveals that a `Register` may be thought of as `recording` a `Sale`.

1. A `Register` creates (records) the `Sale`
2. A `Sale` creates an empty collection to record `SalesLineItem` instances
