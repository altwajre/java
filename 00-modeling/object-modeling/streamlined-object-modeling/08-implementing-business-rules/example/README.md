# Implementing Collaboration Rules Example

How to implement collaboration rules using the `Person - TeamMember` and `Team - TeamMember` collaborations.

## Person - TeamMember - Team Collaboration Rules

As an `actor`, a `person` has no collaboration rules for its `roles`, which include team member. The `team member` plays as a `specific` in `generic - specific` collaboration with its person, and plays as a `part` in a `whole - part` collaboration with its team. Because it acts as these pattern players, the team member carries most of the load for checking the collaboration rules.

### Team member: Establish person collaboration rules

1. A team member cannot collaborate with a person if the team member already has a person collaborator
2. A team member cannot collaborate with a person who lacks a valid email
3. A team member cannot collaborate with a person if the team member's team already includes another team member with the same person

> Explanation

Rule #1 is multiplicity rule. A team member can collaborate
Rule #2 is a property rule. A person lacking a valid email property cannot collaborate
Rule #3 is a conflict rule. A team member cannot collaborate with a person if the person conflicts with the team member's present team collaborator.

### Team member: Dissolve person collaboration rules.

1. A team member cannot dissolve its collaboration with its person if the team member belongs to a team.

> Explanation

Rule #1 is a state rule. A team member on a team is in an improper state for dissolving its person collaboration.

### Team member: Establish team collaboration rules.

1. A team member cannot collaborate with a team if the team member already has a team collaborator.
2. A team member cannot collaborate with a team if the team already includes another team member with the same person.

> Explanation

Rule #1 is a multiplicity rule.
Rule #2 is a conflict rule.

### Team member: Dissolve team collaboration rules

1. A team member cannot dissolve its collaboration with its team if the team member has nominations.

> Explanation

Rule #1 is a state rule. A team member with nominations is in an improper state for dissolving its team collaboration.

### Team: Establish team member collaboration rules

1. A team cannot collaborate with a team member if the team member is a chair, and the team's format will not allow it to add another chair team member.

> Explanation

Rule #1 is a property rule. This rule uses properties from both the team and team member. Because the team has the "standard" used for comparison, it owns the rule. This rule also applies for cross-property validation.

## Plan of Attack

The plan is to update the person, team member, and team object definitions to include the above collaboration rules.

> Object definitions changes:

- Expand the conduct business interfaces
- Implement the "test" and "do" methods
- Revise the collaboration accessors
