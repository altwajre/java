# TRANSACTION – SPECIFIC OBJECT DEFINITIONS

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

Collaboration pattern described by the `transaction - specific` pattern are:
- transaction - role
- transaction - specific item
- transaction - place
- follow-up transaction - transaction
- line item - specific item

## Transaction - Specific vs Generic - Specific

Transaction - specific collaborations look deceptively like generic - specific collaborations; however, a transaction is more active collaborator than a generic. A single transaction often units multiple collaboration patterns. Because transactions coordinate between many collaboration patterns, they are called the "glue" of the object model.

A transaction coordinates between many collaboration patterns.

## Transaction - Specific Implementation Guidelines

Here are the important facts about transaction - specific collaborations that shape how they are implemented:

- No object inheritance is involved
- No collaborator is more "specific" than another
- A transaction can have multiple specifics, but each is of a different type
- A specific can have multiple transactions of the same type
- A specific can have different types of transactions

As with whole - part, profile interfaces are not necessary; conduct business interfaces are always recommended.

The specific work principle does not apply with transaction - specific. Instead, the relevant rule of thumb arises from the fact that a transaction exists to record the interaction of a person and a thing at given place. This implies that a transaction cannot exist without its specifics, namely its role, specific item, and place. The reverse is clearly not true because a person, place, and thing can exist independently of any transactions.

> Principle 78: Let the Coordinate Direct

When different types of object are united by a single common coordinator and must work toward a common goal, allow the coordinator to direct the actions.

Read that principle carefully. It does not say to put all the work into a central controller object. Each object is still responsible for performing its own share of the work, but only when cued from the coordinator.

Here is how the directing coordinator principle applies to the transaction - specific DIAPER:

- Printing - A transaction asks its specifics to print themselves
- Comparing - A transaction asks its specifics to compare themselves
- Testing - A transaction test object uses specific test objects

A transaction brings other objects, specifics, together. The specifics are not usually of the same type. Rather than forcing the different types into one collection of specifics, the recommended approach is to define a separate instance variable for each type.

Multiple transactions for a specific record that object's history of interactions. Also, analyze transactions services do work across the transactions of a specific. The natural implementation defines an instance variable within the specific to hold the transactions.
