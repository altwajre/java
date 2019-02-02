# Collaboration Patterns

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/ch03.html

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/apb.html

`Collaboration patterns` describe the “what” of an interaction—the objects involved and the rules governing those interactions.

`Collaboration patterns` are `not design patterns`. `Design patterns` focus on efficiency, reuse, and pluggability, and while these are important goals, they are concerned more with `how` to effect an interaction, not `what` is involved in it.

> two pattern players

Collaboration patterns are, by definition, patterns involving `two pattern players`. Each collaboration pattern involves two of the pattern players described by the object selection principles.

1. Larger patterns arise when two collaborations are `snapped together` by sharing a common pattern player, or

2. When collaborations are `merged` by sharing an object that is acting as several different pattern players.

## Real-World Modeling

- Any comprehensive methodology for modeling the real world requires a viewpoint, a vocabulary, and an organizational method.
- Our viewpoint is that real world consists of entities having simple or complex structures, interacting with one another in space, time, and many contexts.

## Three Fundamental Patterns

> Generic-Specific

- actor - role
- item - specific item
- composite transaction - line item

> Whole-Part

- outer place - place
- assembly - part
- container - content
- group - member

> Specific-Transaction

- role - transaction
- place - transaction
- specific item - transaction
- transaction - follow-up transaction
- specific item - line item

`Collaboration patterns` are the embodiments of the object selection Principle.

### Generic-Specific

#### Actor `parent` --- `0..*` Role

Example: In a supply-chain application, a company can be both a supplier of goods and services and a distributor that buys from suppliers

                  Company (Actor)
                  /            \
        Supplier (Role)    Distributor (Role)

> Use to model the participation of a person, organization, place, or thing in a context

- An `actor` knows about zero to many `roles`, but typically takes on only one of each kind
- A `role` represents a unique view of its `actor` with a context. The `role` depends on its `actor` and cannot exist without it.

#### Item `parent` --- `0..*` SpecificItem

Example: A video title Big Sinking Ship, describes a set of videotapes available for renting.

VideoTitle (Item)
       |
VideoTape (Specific Item)

> Use to model a thing that exists in several distinct variations

- An `item` is the common description for zero to many `specific items`
- A `specific item` knows and depends on one `item`. The `specific item's` property values distinguish it from other `specific items` described by the same `item`

#### CompositeTransaction `parent` <>--- `1..*` LineItem

Example: An online entertainment site contracts with content (usually video) producers for the rights to how portions of the producer's content titles on the site. The license agreement contains title terms for each content title covered.

ContentProducer (Role)
   |
TitleLicenseAgreement (Composite Transaction)
   |
TitleTerms (Line Item)
   |
ContentTitle (Specific Item)

> Use to record an event involving more than one thing

- A `composite transaction` must contain at least one `line item`
- A `line item` knows only one `composite transaction`. The `line item` depends on the `composite transaction` and cannot exist without it.

### Whole-Part

#### OuterPlace `0..1` <>--- `1..*` Place

Example: A manufacturing warehouse receives delivers in its loading areas and dispenses shipments from it shipping areas.

                   ManufacturingWarehouse (Outer Place)
                     /                              \
           LoadingArea (Place)         ShippingArea (Place)

> Use to model a hierarchy of locations where events happen

- An `outer place` is the container for zero or more `Places`
- A `place` knows at most one `outer place`. The `place`'s location depends on the location of its `outer place`

#### Assembly `0..1` <>--- `1..*` Part

Example: A computer workstation is assembled from computer components. The price, weight and availability of the workstation are partly determined from the characteristics of its parts.

Workstation (Assembly)
      |
Component (Part)

> Use to model ensemble of things

- An `assembly` has one or more `parts`. Its `parts` determine its properties, and the `assembly` cannot exist without exist without them.
- A `part` belongs to at most one assembly at a time. The `part` can exist on tits own

#### Containers `0..1` <>--- `0..*` Content

Example: In a distribution center for a manufacturing plant, cases of product are stored in pallets that are then loaded onto delivery trucks.

Pallet (Container)
      |
Case (Content)

> Use to model a receptacle for things

- A `container` holds zero or more `content` objects. Unlike an `assembly`, it can be empty
- A `content` object can be in at most one `container` at a time. The `content` object can exist on its own

#### Group `0..*` <>--- `0..*` Member

Example: A product catalog puts products into categories, such as sportswear, home and garden, etc

CatalogCategory (Group)
      |
Product (Member)

> Use to model a classification of things

- A `group` contains zero or more `members`. `Groups` are used to classify objects
- A `member`, unlike a `part` or `content` objects, can belong to more than one `group`

### Specific-Transaction

#### Role `1` --- `0..*` Transaction

Example: In the office supplies example, a `sales order` placed over the telephone by `customer` includes the participation of the `sales rep` taking the order

Customer (Role)
     |
SalesOrder (Transaction)
     |
SalesClerk (Role)

> Use to record participates in events.

- A `transaction` knows one `role`, the doer of its interaction
- A `role` knows about zero or more `transactions`. The `role` provides a contextual description of the person, organization, thing, or place involved in the `transaction`

#### Place `1` --- `0..*` Transaction

Example: In a manufacturing warehouse, deliveries arrive to one loading area. To track deliveries, loading areas must be uniquely identified.

ManufacturingWarehouse (Outer Place)
     |
LoadingArea (Place)
     |
Delivery (Transaction)

> Use to record where an event happens

- A `transaction` occurs at one `place`
- A `place` knows about zero to many `transactions`. The `transactions` record the history of interactions at the place.

#### SpecificItem `1` --- `0..*` Transaction

Example: An online cattle breeding site allows ranchers to search for and reserve bulls for breeding services with their cows. A bull object belonging to a cattle breed that contains the general characteristics of the breed represents each bull on the site

Rancher (Role)
   |
BreedingReservation (Transaction)
   |
Bull (Specific Item)

> Use to record an event involving a single thing.

- A `transaction` knows about one `specific item`
- A `specific item` can be involved in

#### Transaction `1` --- `0..*` FollowupTransaction

Example: An e-commerce site allows a product (an SKU) to be ordered and shipped. Depending on the availabilities of the products ordered and if there are multiple ship-to addresses, multiple shipments may be required to deliver the entire order to the customer

Product (Specific Item)
   |
Order (Transaction)
   |
ShipmentLineItem (Follow-up Transaction)

> Use to record an event that occurs only after a previous event

- A `transaction` knows about some number of `follow-up transactions`
- A `follow-up transaction` follows and depends on exactly one previous `transaction`

#### SpecificItem `1` --- `0..*` LineItem

Example: In a video store customers can rent multiple videotapes in a single rental transaction

Rental (Composite Transaction)
   |
RentalLineItem (Line Item)
   |
VideoTape (Specific Item)

> Use to record the particular involvement of a thing in an event involving multiple things

- A `specific item` can be involved in zero to many `line items`
- A `line item` knows exactly one `specific item`. The `line item` captures details about the `specific item's` interaction with a `composite transaction`
