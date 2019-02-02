# Coordinating Collaboration Rules

Collaboration rules govern whether two objects can establish or dissolve a collaboration with each other. Because two objects are involved, the collaboration rule checking must be coordinated so that:
1, both objects can do rule checking
2, rule checking happens when either object establishes or dissolves the collaboration.

## Dual Rule Checking

All the collaboration rules cannot be consolidated in one object. While putting all rules in one place may make the implementation easier in the short-term, it destroys pluggability, extensibility, and scalability.

> Principle 84: Dual Rule Checking

To achieve pluggability, extensibility, and scalability, each object must check its own collaboration rules.

## Commutative Rule Checking

Collaboration rules do not care how objects were brought together or pulled apart.

EXAMPLE—Consider the Team – TeamMember collaboration. Regardless of whether a team member is added to a team through the “add team member” accessor or a team is added to a team member through the “add team” accessor, all the collaboration rules need to be checked.

> Principle 85: Commutative Rule Checking

Implement collaboration rules so that either collaborator can request that the rules be checked.

## Streamlining To A Director

> Principle 88: Streamlining Collaboration Accessors

To streamline the collaboration accessors, allow one collaborator to delegate the process of establishing and dissolving the collaboration to the other collaborator.

> Principle 89: Choosing Your Director

To find the director of a streamlined collaboration, choose the `specific` of a `generic - specific`, choose the `part` of a `whole - part`, and choose the `transaction` of a `transaction - specific`
