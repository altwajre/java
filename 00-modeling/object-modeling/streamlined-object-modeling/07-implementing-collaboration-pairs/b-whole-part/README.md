# WHOLE – PART OBJECT DEFINITIONS

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

## whole - part collaboration pattern

- container - content
- assembly - part
- group - member
- outer place - place

> coding the `whole - part` requires only these few modifications to the `generic - specific` `DIAPER`

- Profile interfaces are unnecessary because it does not use object inheritance
- The `whole` always allows for multiple parts
- A `part` may have multiple wholes (e.g., group - member collaboration)
- A `part` may exist without a `whole`

Putting the work in the `specific` was a major determinant in coding `generic - specific` collaborations.
Apply that same principle when coding `whole - part` collaborations impacts the DIAPER steps.

> Principle 76: Part Carries the Load

When work requires cooperation between a `whole` collaborator and a `part` collaborator, encapsulate the majority of the effort within the `part` collaborator.

> Example

A team member belongs to exactly one team. Each team has a description and format, which can be one of the following: no chair, single chair, or multiple chairs.
The team's format defines the following collaboration rule: A team member cannot be added to a team if the team member has a chair role and the team's format cannot support a chair being added to the team.
