# Structured Programming

https://www.safaribooksonline.com/library/view/clean-architecture-a/9780134494272/ch4.xhtml

It replaces the jumps (goto) with the more familiar if/then/else and do/while/until constructs.

## PROOF

All programs can be constructed from just three structures: sequence, selection, and iteration.

## FUNCTIONAL DECOMPOSITION

Structured programming allows modules to be recursively decomposed into provable units.
Programmers could break down large proposed systems into modules and components that could be further broken down into tiny provable functions.

## Tests

Testing shows the presence, not the absence of bugs.
A program can be proven incorrect by a test, but it cannot be proven correct.
All the tests can do, after sufficient testing effort, is allow us to deem a program to be correct enough for our purposes.
Structured programming forces us to recursively decompose a program into a set of small provable functions.
Then use tests to try to prove the small provable functions incorrect.
If tests pass, then we deem the functions be correct enough for our purposes.
