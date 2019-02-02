# Finding Objects

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/ch02.html

1. good object think
2. good object selection

## Object Think

Object think is key to properly encapsulating data and functionality within objects.

Good object thinkers apply consistent principles for distributing data and functions; they can explain and justify the results in their object models by relating them back to the objects in the business domain.

1. `Rule one` `personify objects` and conceive of them as active, knowledgeable entities. From the the object personification point of view, a domain of entities and processes becomes a collection of active objects capable of undertaking complex actions in response to outside requests. A model produced by object personification has an organization that reflects the natural structure of the domain.

2. `Rule two` adopt the `first-person` voice when discussing objects. Under the first-person perspective, all statements about an object are expressed using the pronoun "I". Speaking of objects in the first-person voice is useful to determining precisely what `responsibilities` an object needs, along the lines of "If I am this object, do I really need to know about X, or really need to be able to do Y?"

3. `Rule three` `transform knowledge` about an object and the functions performed on it into responsibilities of the object. Under the responsible objects perspective, `functions` performed on an object become services the object performs; data recorded about an object become properties the object maintains; and links to other things become collaborations with other objects, formed according to business rules.

> Principle 7: Personify Objects

Object model a domain by imagining its entities as active, knowing objects, capable of performing complex actions.

> Principle 8: Give Objects Responsibilities

Turn information about a real-world entity and the actions performed on it into responsibilities of the object representing the entity.

> Principle 9: Object’s Responsibilities

An object’s responsibilities are: whom I know—my collaborations with others; what I do—my services; and what I know—my properties.

> Principle 10: Talk Like an Object

To scope an object’s responsibilities, imagine yourself as the object, and adopt the first-person voice when discussing it.

## Object Selection

Finding objects is easy if you know how to and where to look for them. How to look is simple; use object think to ask questions of domain experts. Where to looks is straightforward, too;
look for `objects` in the following `four categories: people, places, things, and events`.

### People

People play a big part in nearly all domains. Information to capture about people includes details identifying individuals, the different contexts in which they can participate, their privileges and duties in those contexts, and records of their interactions. Often people participate not on their own behalf, but on behalf of a company or agency; however, object think dictates that it is the organization that is bidding, buying, and selling, and otherwise acting like a single individual. To model a person or an organization that can participate in the system, use an actor object. Model the participation and actions of the person or organization with separate objects.

A simple of object modeling is that every action by a person or an organization happens within a context. A context is focused piece of the business domain that concentrates on the information and rules necessary to reach a single goal. Putting an action within a context helps ensure the action is only undertaken by those able to participate in that context, and that the action only involves other people, places, and things within that context.

> Principle 11: The People Principle

Use an actor object to model individual people participating in a system. Also use an actor object to model an organization of people participating in a system as a single entity.

> Principle 12: The Context Principle

A context of participation exists whenever a person or organization undertakes actions that are tracked and recorded. Actions that require different permissions or information from the person or organization belong in different contexts.

> Principle 13: The Role Principle

For each context an entity participates in, create a separate role object. Put the information and permissions needed for that context into the role.

### Places

Every recorded action by a person or organization happens in a given place.

Places are often hierarchical for the following reasons:
1. a position for a localized place cannot be fully specified without putting it inside a larger place
2. recorded actions in localized places are rolled up into larger places for auditing or statistical comparison. The smallest place of interest is where recorded actions occur.

To model a location where `recorded actions occur`, use a `place object`
To model a location that `contains places`, use an `outer place object`

> Principle 14: The Place Principle

Model a location where recorded actions occur with a place object. Model a hierarchical location with an outer place containing a place. Model the uses of a place or outer place in different contexts with role objects.

### Things

Every real-world action involves a subject that is the entity being acted upon. A tangible entity that can be the subject of an action is considered a thing. When people and places are the subjects of actions, they are acting like things.

> To describe a real-world thing requires two objects.

The `first object provides a general`, high-level description of the thing that is abstract enough to be shared with other similar things. In effect, this general description defines a set of related things.

The `second object is a specific description` that extends the general description and distinguishes the thing from others in the sets.

To model the general description of a set of things, use an `item` object, which is a reusable description for many particular things. Use a `specific item` to model the properties that distinguish a particular thing from other things with the same item description.

> Principle 15: The Thing Principle

Model a thing with two objects: an `item` that acts as a description defining a set containing similar things, and a `specific item` that distinguishes a particular thing from others in the set. Model the uses of a thing in different contexts with role objects.

### Aggregate Things

As with places, a thing can be tracked at different hierarchical levels of scale. Going up the scale, a thing can be placed within a container, classified within one or more groups, or fitted as a part into an assembly; going down with the scale, a thing can act as a container, a group, or an assembly.

> container - Content

To model a thing that is a receptacle or storage place for other things, use a container object. Use a content object to represent a thing that is placed in a container. A content object can only be in at most one container at a given time.

> group - member

To categories a collection of things, use a `group` object. A `group` is different from a `container`, because it denotes categorization, not physical containment. A group is also different from an item. Recall that an item acts a common description for all things in a set, whereas the things belonging to a group need not be related other than by their inclusion in the group. Use a member object to represent a thing that belongs to one or more groups.

> assembly - part

To model a thing constructed from other things, use an assembly object. Unlike a container and a group, an assembly cannot exist without its constituent parts. Use a part object to represent the component things that make up an assembly.

Example - A made-to-order computer workstation is assembled from components selected by online by a customer.

> Principle 16: The Aggregate Thing Principle

Model a receptacle of things as a container with content objects. Model a classification of things as a group with member objects. Model an ensemble of things with an assembly of part objects.

### Events

An event is an interaction between people, places and things. Because they tie together the other three categories of objects, events are considered the historical glue in the object model. A simple event involves people interacting at a place with only one thing. Complex events involve multiple things and are discussed in the next section.

Example - A mechanic inspects an automobile on a given date and time and records the results and success or failure of the inspection.

To model simple events involving only one thing, use a transaction object. Transactions are either point-in-time events that represent a momentary interaction, or they are time-interval events that occur over a long-term duration.

Example - Common point-in-time events are orders, purchases, returns, deposits, withdrawals, arrivals, departures, shipments, and deliveries. Common time-interval events are leases, rentals, job assignments, memberships, and itineraries.

To model a point-in-time event, use a transaction that knows about a single timestamp. For a time-interval event, use a transaction that knows about the starting and the ending timestamps. History events are a special kind of time-interval event. A history event knows information about a person, place, or thing during a specified time span.

Example - An e-commerce system assigns each of its products a price with an effective date range; for legal reasons, the price history must be retained for each product. For each assembly line, a manufacturing plant keeps a history of its products produced during a given time period. This information is used to calculate key performance measures for the line and plant.

> Principle 17: The Event Principle

Model the event of people interacting at a place with a thing as a transaction object. Model a point-in-time interaction as a transaction with a single timestamp; model a time-interval interaction as a transaction object with multiple timestamps.

> Principle 18: The History Principle

To record historical or time-sensitive information about a person, place, or thing, use a time-interval transaction.

### Composite Events

Composite events involve people interacting at a place with one or more things. To distinguish and track each interaction with a single thing, a Composite event contains smaller events, one for each thing. The Composite event is responsible for summary information and behaviors that work across all the interacting things.

To model a composite event, use composite transaction. Use a line item to record the interaction details for a single thing within a composite transaction. A line item is a dependent event in that it cannot exist by itself; it exists only as part of the composite transaction.

Example - An e-commerce order is a composite transaction with a line item for each product included in the order. The line item for each product included in the order. The line item for a single product contains the quantity ordered, shipping information, and gift wrap and wrap and gift message options.

### Follow-up Events

Often an interaction between given set of people, places, and things is followed by another interaction with some of the same people, places, and thing. In general, simple events are followed by simple events, and composite events are followed by composite events.

Example - Common follow-up events are a delivery following a shipment, an arrival at one place following a departure at another, and a return at store branch following a sale at a different store branch.

To model an event following another event, use a follow-up transaction object. Follow-up transactions are not the same as line items. A line item records details about a thing inside a composite event. A follow-up transaction describes another event at a later time.

> Principle 19: The Composite Event Principle

Model people interacting at a place with multiple things as a composite transaction; for each thing involved, include a line item to capture specific interaction details.

> Principle 20: The Follow-up Event Principle

Model an event that follows and depends on a previous event with a follow-up transaction.

## The Pattern Players

> People

- Actor
`Principle 11: The People Principle`
Use an `actor` object to model individual people participating in a system. Also use an `actor` object to model an organization of people participating in a system as single entity.

- Role
`Principle 13: The Role Principle`
For each context an entity participates in, create a separate `role` object. Put the information and permissions needed for that context into the `role`.

> Places

- Place
- Outer Place
`Principle 14: The Place Principle`
Model a location where recored actions occur with a `place` object.
Model a hierarchical location with an `outer place` containing a `place`.
Model the uses of a `place` or `outer place` in different contexts with `role` objects.

> Things

- Item
- Specific Item
`Principle 15: The Thing Principle`
Model a thing with two objects: an `item` that acts as a description defining a set containing similar things
A `specific item` that distinguishes a particular thing from others in the set.
Model the uses of a thing in different contexts with `role` objects.

- Assembly
- Part
- Container
- Content
- Group
- Member
`Principle 16: The Aggregate Thing Principle`
Model a receptacle of things as a `container` with `content` objects. Model a classification of things as a `group` with `member` objects. Model an ensemble of things with `assembly` of `part` objects

> Events

- Transaction
`Principle 17: The Event Principle`
Model the event of people interacting at a place with a thing as a `transaction` object. Model a point-in-time interaction as a `transaction` with a single timestamp; model a time-interval interaction as a `transaction` object with multiple timestamps.

- Composite Transaction
- Line Item
`Principle 19: The Composite Event Principle`
Model people interacting at a place with multiple things as a `composite transaction`; for each thing involved, included a `line item` to capture specific interaction details.

- Follow-up Transaction
`Principle 20: The Follow-up Event Principle`
Model an event that follows and depends on a previous event with a `follow-up transaction`.
