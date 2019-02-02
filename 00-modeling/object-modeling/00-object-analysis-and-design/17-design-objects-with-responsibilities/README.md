# Chapter 17. GRASP: Designing Objects with Responsibilities

https://learning.oreilly.com/library/view/applying-uml-and/0131489062/ch17.html

GRASP: `General Responsibility Assignment Software Patterns`

Understanding responsibilities is key to good object-oriented design.

# Responsibilities and Responsibility-Driven Design

## Doing responsibilities

a `Sale` (transaction object) is responsible for creating SalesLineItems

- doing something itself, such as creating an object or doing an calculation
- initiating action in other objects
- controlling and coordinating activities in other objects

## Knowing responsibilities

a `Sale` (transaction object) is responsible for knowing its total

- knowing about private encapsulated data
- knowing about related objects
- knowing about things it can derive or calculate
