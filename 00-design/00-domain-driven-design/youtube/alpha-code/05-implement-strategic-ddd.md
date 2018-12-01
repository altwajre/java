# Implementing Strategic Domain Driven Design

https://www.youtube.com/watch?v=b6D_NTgzmhs&list=PLZBNtT95PIW3BPNYF5pYOi4MJjg_boXCG&index=6

https://www.boldare.com/blog/event-storming-guide/

Building a software is a learning process, working code is a side effect.

How would you discuss your understanding with domain expert?

## Event Storming

- An exercise for creating Domain Models for strategic design
- In simple words it is a brain storming workshop among domain experts and technology people to understand the events in a system
- It is aimed to achieve a common understanding of the domain in which software must operate

> How does event storming work?

- First, bring the right people in
- Then take sticky notes and color code them to events, commands, policies, processes, errors, roles and aggregates etc
- Take a white board and start writing interesting events in sequence
[Items Browsed] -> [Item purchased] -> [Item shipped] -> [Item delivered]
- Then start adding commands, aggregates, policies etc
    [System action]      [System action]
  [Browse items]       [Item purchased]
[User action]        [User action]
- After the first pass, start identifying bounded contexts

|      Catalog       |  Order management    |
---------------------------------------------
|    [System action] |      [System action] |
|  [Browse items]    |   [Item purchased]   |
|[User action]       | [User action]        |
