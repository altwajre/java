# e-commerce

# Finding Objects

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch02.html

## Events

To model a point-in-time event, use a transaction that knows about a single timestamp. For a time-interval event, use a transaction that knows about the starting and the ending timestamps. History events are a special kind of time-interval event. A history event knows information about a person, place, or thing during a specified time span.

EXAMPLE— An e-commerce system assigns each of its products a price with an effective date range; for legal reasons, the price history must be retained for each product. For each assembly line, a manufacturing plant keeps a history of its products produced during a given time period. This information is used to calculate key performance measures for the line and plant.

> Principle 17: The Event Principle

Model the event of people interacting at a place with a thing as a transaction object. Model a point-in-time interaction as a transaction with a single timestamp; model a time-interval interaction as a transaction object with multiple timestamps.

> Principle 18: The History Principle

To record historical or time-sensitive information about a person, place, or thing, use a time-interval transaction.

## Composite Events

Composite events involve people interacting at a place with one or more things. To distinguish and track each interaction with a single thing, a composite event contains smaller events, one for each thing.9 The composite event is responsible for summary information and behaviors that work across all the interacting things.

To model a composite event, use a composite transaction. Use a line item to record the interaction details for a single thing within a composite transaction. A line item is a dependent event in that it cannot exist by itself; it exists only as part of the composite transaction.

EXAMPLE—An e-commerce order is a composite transaction with a line item for each product included in the order. The line item for a single product contains the quantity ordered, shipping information, and gift wrap and gift message options.

> Principle 19: The Composite Event Principle

Model people interacting at a place with multiple things as a composite transaction; for each thing involved, include a line item to capture specific interaction details.

# Collaboration Patterns

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch03.html

## Actor Responsibilities

An actor can know about multiple roles, but can take on only one of each kind. The actor is responsible for knowing information—properties, services, and collaborations—that is relevant across all contexts.

EXAMPLE—An e-commerce startup tracks people who are customers, employees, and brokers. A person (actor) can play multiple roles, but can only take on at most one customer role, one employee role, and one broker role

## Line Item Responsibilities

A line item knows exactly one composite transaction, and it is entirely dependent on that composite transaction; the line item cannot be in a valid state without its composite transaction, and it cannot be transferred to another composite transaction. The line item contains details about one of the things involved in the event.

EXAMPLE—In the online entertainment example, each title terms object includes the number of minutes of content that can be extracted from the content title for viewing on the site, the payment terms for viewing the title, and the commission rates for the title when the content showing results in any e-commerce revenues

## Transaction Responsibilities

A transaction knows about some number of follow-up transactions. These are often the next steps in processes.

EXAMPLE—An e-commerce site allows a product (an SKU) to be ordered and shipped. Depending on the availabilities of the products ordered and if there are multiple ship-to addresses, multiple shipments may be required to deliver the entire order to the customer

## Follow-up Transaction Responsibilities

A follow-up transaction knows about one transaction; it cannot exist prior to this transaction or be in a valid state without it. Also, since the follow-up transaction is itself a transaction, it may know its own role and place, or it may be a composite transaction.

EXAMPLE—For each line item of product ordered from the e-commerce site, there are some follow-up shipment line items to track how many of the product were sent in each follow-up shipment

# Services and Properties

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch05.html

## Tracking Properties

Tracking properties are special descriptive properties used to distinguish objects and give them unique identities. During the object modeling sessions, ask domain experts how they track and distinguish between customers, orders, students, employees, shipments, parts, etc. Analysis object models should not include non-descriptive keys or object IDs in the object diagram. These may be added later during design.

EXAMPLE—An online e-commerce site tracks its registered customers by their email addresses. A college tracks its students by their Social Security Numbers. A fulfillment center tracks its shipments by carrier tracking numbers.

## Historical Properties

Most properties can change values, but for some properties, the history of past values is important to the business processing. When an object has an historical property, the property is promoted to a history object collaborator,21 the default read accessor retrieves the value in the most current history object, a special read accessor retrieves the value on a given date, and the write accessor creates a new history object.

EXAMPLE—An e-commerce site needs to remember price changes for its products. Each product has one or more price history objects, which include a price and its effective dates.

# Object Inheritance Strategies

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch06.html

## OBJECT INHERITANCE AND COLLABORATIONS

A child object inherits most of the collaborations of its parent. As with properties, for every collaborator a child object inherits from its parent, the child has a read accessor that retrieves the collaborator from the parent. To the outside world, which requests the collaborator from the child, the fact that the collaborator actually belongs to the parent is hidden. As with property accessors, collaboration accessors, which establish or dissolve collaborations, are not depicted in the object model but assumed.

EXAMPLE—An e-commerce clothing store categorizes each of its products into one or more categories. All products of the same product description belong to the same categories, so their parent object is the one categorized. When a product is asked for its containing categories, it delegates the request to its parent, product description. And when a product is asked if it is in a particular category, it delegates the request to its parent. These services belong in the product description and are assumed to exist in the product through object inheritance.

## Groups, Assemblies, and Containers

Object inheritance involves two objects representing one entity or event. If the parent is part of any groups, assemblies, or within any containers, the entity or event being represented is assumed to be part of them. Since the parent and child both represent the entity, the child object inherits the knowledge of the groups, assemblies, and containers.

EXAMPLE—In the e-commerce clothing store example, a product object inherits the categories (groups) of its parent product description

## Child Object Collaborations

When parent objects have multiple child objects, a child does not object inherit the other child objects. The reason for this is that each child represents a more specific version of the parent. If each child inherited all of the other children’s specific details, there would be no way to tell them apart, and their usefulness would be limited at best. In the case of the actor – role pattern, each role object would have more information than would be useful in its context. That makes things more complicated and is contrary to the reason object inheritance is used.

EXAMPLE—An e-commerce system includes people who are customers, employees, and brokers. A person can take on multiple roles; however, a role does not know about the other roles. For example, a customer object does not object inherit knowledge of the person’s employee or broker roles, if they exist.

## OVERRIDING OBJECT INHERITANCE

Properties and collaborations in the parent object frequently serve as defaults child objects can override. This section discusses techniques for selectively overriding object inherited information.

### Overriding Properties

To depict a property overridden by a child, duplicate the property in the child. The read accessor for the property checks first for the property value in the child, and returns that value if it is set. Otherwise, the read accessor retrieves the property value from the parent.

EXAMPLE—An e-commerce site marks down product SKUs that are not selling well because they have unpopular styles or colors out of season. A marked down product has its own price, while a product not marked down uses the price in its parent product description.

### Overriding Aggregate Collaborations

Group collaborators classify objects, and many domains allow child objects to classify themselves in different groups than their parent’s, business rules permitting. This permits more finely grained classification. In these domains, a child belongs to all groups obtained by the union of its parent’s and its own group collaborators. Parts construct their assembly collaborators, and some domains allow both a child and its parent to be parts of different assemblies. Since no object can be in more than one assembly, the child uses its parent’s assembly, unless it has its own assembly collaborator. A child cannot use the union of its own and its parent’s assembly. Containers are similar to assemblies. A child object that overrides its parent’s container uses its own container, not the union of the two.

EXAMPLE—An e-commerce site has broad product categories for classifying product descriptions describing many product SKUs. Special, narrow categories classify product SKUs based on colors, sizes, styles, etc. A product SKU has two services, one of which is the normal collaboration accessor that returns its product category collaborators and defaults to its parent’s. The second service returns all its product categories: the union of its own and its parent’s. The product SKU also has a service to check if it is in a particular category. This service also uses the union of the category collaborators.
