# Implementing Business Rules

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch08.html

## Two Types of Business Types

- `Property rules`: Used by objects to decide what values are valid for their properties
- `Collaboration rules`: Used by objects to decide whether they can establish or dissolve collaborations

## Property Business Rules

Property rules specify domain-specific limits on a property’s values. This section describes strategies and techniques for implementing business rule checking of property values.

### Implementation Strategies

> Setting a property

1, checking the logical validity of the new value
2, checking the business rule validity of the new value
3, assigning the new value

## Descriptive And Time Property Business Rules

> Business rules for time and descriptive properties

- state transition rules that prevent properties from changing
- value limit rules that constrain property values to specific sets

## Implementing Collaboration Rules

Establishing a collaboration between two objects is a scripted process during which each object is asked to check its own collaboration rules, and if these pass, then each object is asked to do whatever it takes to remember to the other collaborator - set a collaboration variable or add the object reference to a collaboration collection.

### Establishing a Collaboration

Isolate the rule check and collaboration assignment into separate methods.
Isolating the rule check allows subclasses to extend collaboration rules.
Isolating the assignment allows bypassing the business rules when necessary.

> add (collaborator)

Adds the designated collaborator to the object receiving this message ("this" or "self") if the collaboration rules allow

> testAdd (collaborator)

Tests the collaborator against the receiver object's collaboration rules; raises an exception if they fail.

> doAdd (collaborator)

Puts the collaborator into the receiver object's collaboration variable or collection without rule checking

#### Assigning Rules

Follow the usual `rules for assigning` work: `Most Specific Carries the Load` principle, and `Let the Coordinator Direct` principle. Applying these workload principles to the three fundamental patterns yields the following assignments:

- generic delegates to specific
- whole delegates to a part
- specific delegates to a transaction

> Principle 88: Streamlining Collaboration Accessors

To streamline the collaboration accessors, allow one collaborator to delegate the process of establishing and dissolving the collaboration to the other collaborator.

> Principle 89: Choosing Your Director

To find the director of a streamlined collaboration, choose the `specific` of a `generic - specific`, choose the `part` of a `whole - part`, and choose the `transaction` of a `transaction - specific`

### Dissolving a Collaboration

The process for dissolving a collaboration between two objects is very similar to the process for establishing a collaboration. Again, the rule checking and collaboration assignment are isolated into separate methods, all the methods go into the conduct business interface, and the collaboration remove accessors are streamlined according to the "Choosing Your Director" principle.

> Remove (collaborator)

Removes the designated collaborator from the object receiving this message if the collaboration rules allow

> testRemove (collaborator)

Tests the collaborator against the receiver object's collaboration rules; raises an error if they fail

> doRemove (collaborator)

Clears a collaborator from the collaboration variable or removes from a collection without rule checking
