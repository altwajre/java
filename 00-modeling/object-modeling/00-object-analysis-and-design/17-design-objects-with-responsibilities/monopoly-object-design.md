# Monopoly GRASP Object Design

# Creator

> Name

Creator

> Problem

Who creates an A?

> Solution (advice)

Assign `class B` the responsibility to create an `instance of class A` if one of these is tree (the more the better)

- B `contains` or compositely aggregates A
- B records A
- B closely uses A
- B has the initializing data for A

# Information Expert

> Name

Information Expert

> Problem

What is a basic principle by which to assign responsibilities to objects?

> Solution (advice)

Assign a responsibility to the class that has the information needed to fulfill it.

# Low Coupling

> Name

Low Coupling

> Problem

How to reduce the impact of change?

> Solution (advice)

Assign responsibilities so that (unnecessary) coupling remains low. Use this principle to evaluate alternatives.

## Expert Supports Low Coupling

To return to the motivation for Information Expert: it guides us to a choice that supports Low Coupling. Expert asks us to find `the object that has most of the information required for the responsibility` (e.g., Board) and assign responsibility there.

If we put the responsibility anywhere else (e.g., Dog), the overall coupling will be higher because more information or objects must be shared away from their original source or home, as the squares in the Map collection had to be shared with the Dog, away from their home in the Board.

# Controller

> Name

Controller

> Problem

What first object beyond the UI layer receives and coordinates ("controls") a system operation?

> Solution (advice)

Assign the responsibility to an object representing one of these choices:

- Represents the overall `system`, a `root object`, a device that the software is running within, or a major subsystem (these are all variations of a facade controller)
- Represents a use case scenario within which the system operation occurs (a use case or session controller)

# High Cohesion

Low cohesion classes often represent a very `large grain` of abstraction or have taken on responsibilities that should have been delegated to other objects.

> Name

High Cohesion

> Problem

How to keep objects focused, understandable, and manageable, and as a side effect, support Low Coupling?

> Solution (advice)

Assign responsibilities so that cohesion remains high. Use this to evaluate alternatives.
