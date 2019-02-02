# SNAPPING TOGETHER COLLABORATIONS

The simplest forms of collaboration combinations are the "snap-togethers". Two collaborations snap together if they `share a common pattern player`, meaning that the same object acts as the `same pattern player` in both collaborations.

`Transactions` are the glue that brings people, places, and things together. As such, they are involved in many of the snap-together collaborations.

## Transaction - Role - Actor

The `transaction - role - actor` pattern represents people participating in a context and interacting with `things` and `places` that are recorded as `events`.

## Transaction - Specific Item - Item

The `transaction - specific item - item` pattern represents an event involving a distinct individual thing that shares a common description with several related things.

## Transaction - Place - Outer Place

The `transaction - place - outer place` pattern represents an event happening at a place that can be contained in a hierarchy of larger places.

## Follow-up Transaction

`Follow-up transactions` happen after the original event recorded by a `transaction`, and they can involve the same people, places, and things as the original, or they can involve new ones.

> Involving same people, places, things

- role - transaction - follow-up transaction
- place - transaction - follow-up transaction
- specific item - transaction - follow-up transaction

> Involving different people, places, things

- transaction - follow-up transaction - role
- transaction - follow-up transaction - place
- transaction - follow-up transaction - specific item

EXAMPLE- A `vehicle registration`(transaction) happens in a county of a state, and it involves a particular `vehicle`(specific item) and its `owner`(role). Each vehicle maps to a vehicle description that includes its make, model, year, and manufacturer. A `registration can be renewed`(follow-up transaction) multiple times for the same owner and vehicle at the same or a different county.

## Composite Transaction - Line item - Specific Item - Item

A `composite transaction` represents a complex event involving many things. `Line items` and `specific items` represent the involvement of the things in the events.
