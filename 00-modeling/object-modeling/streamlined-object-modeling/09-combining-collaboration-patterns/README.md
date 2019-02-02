# Combining Collaboration Patterns

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch09.html

## Patterns as Molecules

Every pattern-based methodology has as is ultimate goal the construction of object models from patterns. The popular metaphor compares patterns to "building blocks" that connect and snap together to build larger structures. Streamlined object modeling takes a different approach by starting with the elements of the patterns instead of the patterns themselves. This approach proposes elementary pattern players, similar to chemical elements, and pairs them up to form "molecules" Which pattern players can pair up and form molecules depends on their individual characteristics. Ultimately, only 12 pairs of the pattern players form stable molecules called collaboration patters. This chapter takes the chemical analogy to the next level and shows how to create compound collaboration molecules from the original 12 collaboration patterns.

Because collaboration patterns reflect real-world relationships between objects that are the stable over time, they form sturdy molecules for constructing object models. By placing one of the two collaborating pattern players into another collaboration pair, two collaborations are combined, making a bigger but still stable molecule.

> Compound collaboration molecules come into two varieties; snap-togethers and overlays

- `Snap-together` collaboration molecules `connect` two or more collaboration patterns into larger structure.
- `Overlay` collaboration molecules `merge` pattern players from two or more collaboration patterns, creating new kinds of pattern players, with new characteristics derived from the originals.

## Understanding pattern players

Since combining collaborations depends on the pattern players involved, it is essential to understand what the pattern players represent in the real world and how they participate in collaborations. To this end, every pattern player can be categorized into `empirical category` and `fundamental pattern` categories.

### Empirical Categories

The empirical categories indicate what an object represents in the real world. An object represents either a single real-world entity or a collection of real-world entities.

> Objects representing `single real-world entities` fall into one or more of the following categories:

People - Individuals or collections of individuals participating in contexts and events
Places - Locations or hierarchies of locations where events happen
Thing - Tangible entities that are not people or places
Events - Point-in-time or time-interval interactions between people, places, and things

> Objects representing `collections of real-world objects` are one of the following:

Classification - A group of entities matching some criteria
Receptacle - A container for receiving and storing entities
Ensemble - A whole viewed as a collection of its parts

### Fundamental Categories

The fundamental categories indicate how an object participates in its collaborations. Since all collaborations can be generalized into one of the fundamental patterns, an object can be categorized as one or more of the fundamental pattern players. As shown in previous chapters, putting an object in a fundamental category determines much of its implementation. An object participates in collaborations as one or more of the following:

- generic
- specific
- transaction
- whole
- part

People objects represent entities that can participate in contexts. Typically an actor is a person and a role is his description and behavior in a context; however, things and places can be actors and take on roles too. A more precise category name might be "entity-context" objects.

Pattern players in the generic row are parents to pattern players in the specific row.
A people specific requires a people generic;
a thing specific requires an thing generic;
an event specific requires an event generic

> Example

- A truck can take on the role of delivery vehicle
- A person can take on the role of a customer
- A truck cannot be a customer
- A person cannot be a delivery vehicle

Events record the interactions of people, places, and things. Pattern players with event interactions are specific people, specific things, or local places.
When a transaction has interactions captured by a `follow-up transaction` it is acting like a `specific thing`.

#### Whole - Part Pattern Players

Whole and part pattern players in the classification, receptacle, and ensemble categories represent collections of people, places, things, and events.
The `composite transaction - line item` pattern has some aspects of the `whole - part` relationship, but the dominant relationship between the entities is object inheritance. Therefore, these go in the table as `generic - specific`
