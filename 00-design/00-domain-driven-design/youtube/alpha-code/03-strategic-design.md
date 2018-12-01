# Strategic Design

https://www.youtube.com/watch?v=Evers5npkmE

> What is Strategic Design?

- Object Oriented Design: Think in terms of Objects
- Strategic Design: Think in terms of Contexts

What is Context?
The setting in which a word or statement appears that determines its meaning

> Strategic Design Tools

Ubiquitous Language
Bounded Context
Context Map

## Bounded Context

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

## Problem Space

-----------------------------------------
                Domain

[Sub-Domain]  [Sub-Domain]  [Sub-Domain]
-----------------------------------------

## Solution Space (Context Map)

-----------------------------------------------
                Domain Model

[Bounded Context (UL)] - [Bounded Context (UL)]
          |
[Bounded Context (UL)] - [Bounded Context (UL)]

-----------------------------------------------
