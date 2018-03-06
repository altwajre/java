# What Is Architecture

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch15.xhtml

The primary purpose of architecture is to support the life cycle of the system. Good architecture makes the system easy to understand, easy to develop, easy to maintain, and easy to deploy.
The ultimate goal is to minimize the lifetime cost of the system and to maximize programmer productivity.

Good architects carefully separate details from policy, and then decouple the policy from the details so thoroughly that the policy has no knowledge of the details and does not depend on the details in any way.
Good architects design the policy so that decisions about the details can be delayed and deferred for as long as possible.

## Development

A software system that is hard to develop is not likely to have a healthy lifetime.
The architecture of a system should make that system easy to develop.

## Deployment

A software system must be deployable to be effective.
Make a system that can be easily deployed with a single action.

## Operation

A good software architecture communicates the operational needs of the system.

## Maintenance

Of all the aspects of a software system, maintenance is the most costly.
By separating the system into components, and isolating those components through stable interfaces for future features and greatly reduce the risk of inadvertent breakage.

## Keeping Options Open

A good architect maximizes the number of decisions not made.

The software has two types of value: behavior and structure that is greater because it makes software soft.
The way to keep software soft is to leave as many options open as possible.

Decompose software systems into two major elements: policy and details.
- The policy element embodies all the business rules and procedures; the true value of the system.
- The details enable humans, other systems, and programmers to communicates with the policy, but that do not impact the behavior of the policy at all. They include IO devices, databases, web systems, servers, frameworks, etc.

The goal of the architect is to create a shape for the system that recognizes policy as the most essential element of the system while making the details irrelevant to that policy. This allows decisions about those details to be delayed and deferred. For example, it is not necessary to choose a database system in the early days of development because the high-level policy should not care which kind of database will be used.

# Device Independence

When the Open-Closed Principle was born, the operating systems abstracts the IO devices into software functions that handled unit records that looked like cards. The programs would invoke operating system services that dealt with abstract unit-record devices.
