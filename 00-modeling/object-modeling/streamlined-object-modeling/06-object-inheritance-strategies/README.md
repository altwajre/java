# Object Inheritance Strategies

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch06.html

- object inheritance is `read-only`

## Object Inheritance vs Class Inheritance

Object inheritance differs from class inheritance because it is a relationship between object, not classes.

Instances of the specialization cannot represent instances of the generalization without using some form of object inheritance.

In short, class inheritance is specialization without representation.

Both object inheritance and class inheritance are useful object modeling and coding concepts.

> Object Inheritance

Object Inheritance is a relationship between objects that allows you to create a new `view` on an existing object. The new object, the child, represents another `view` of the existing object, the parent, and the parent can support many such views. Both objects represent the same entity. While the parent object represents the fundamental qualities of the entity, the child object represents the additional qualities the entity takes on within a given context, in an interaction, or as a distinct variation.

When a property or collaboration is inherited through object inheritance, child objects retrieve the value from the parent object, where it is stored. Object inheritance requires additional services of the child to retrieve values, but `no additional storage space`.

Object inheritance is dynamic. Even in typed languages, child objects can be added to and removed from a parent object during the parent's lifetime.

> Class Inheritance

Class Inheritance is a relationship between classes; it allows you to create a new class from an existing one. The new class, the specialization, extends the `structure` of the old class, the generalization, by defining new properties, collaborations, and behaviors.

When a property of collaboration is inherited through class inheritance, objects in the specialization class `have their own storage space` for that property or collaboration reference and can store their own values there, subject to to the business rule constraints.

Class inheritance is static. It occurs between classes that define objects.

> Principle 51: Objects Not Classes

Object inheritance relates two objects, each representing different `views` of the same entity or event. Class inheritance relates two classes, one extending the `structure` defined in the other.

> Principle 52: Representation vs Specialization

Use object inheritance to represent multiple `views` of an entity. Use class inheritance to specialize an existing class of objects.

> Principle 53: Values vs Structure

Object inheritance is the sharing of actual property `values` from a parent object. Class inheritance is the sharing of the `structure` for holding property values from an existing class definition.

> Principle 54: Dynamic vs Static

Object inheritance is dynamic since shared `property values` often change their state during the course of a parent object's lifetime. Class inheritance is static because the `structure` for holding property values rarely changes during a class definition's lifetime.

# ORGANIZATION IS KEY

## Our Prototyping Focus

# OBJECT INHERITANCE IN COLLABORATION PATTERNS

For a parent to keep its constancy across contexts, variations, and interactions, it must remain unaffected by what its child objects do. Accordingly, object inheritance is read-only; a child object may request its parent's properties and collaborations, but it can do nothing to change them. The requests it can make of its parent are limited to those that read information.

## Composite Transaction - Line Item Object Inheritance

The `composite transaction - line item` pattern uses object inheritance to describe the interactions of many entities within a common event. The `composite transaction` is the parent object describing information and behaviors of the overall event. The `line item` is the child object describing an interaction of a single entity and projecting the overall event's profile to the interacting entity.

EXAMPLE- A commission for a sale involves one or more sales agents, and each sales agent earns a percentage of the sale. A commission split describes an agent's involvement in a commission. The date of the date and the total sale amount are properties in the overall commission event that are object inherited by the commission split.

# OBJECT INHERITANCE AND PROPERTIES

## Properties Not to Inherit

> Principle 55: Values Through Services

Use object inheritance to allow a child to share property values with its parent. Add a read accessor in the child for each property value it object inherits from its parent.

> Principle 56: Read But No Write

Never allow a child object to change property values in its parent.

> Principle 57: Only Public Properties

Properties of the parent that are not publicly accessible cannot be object inherited by a child object.

> Principle 58: No Design, Just Business

Don't allow a child to object inherit design properties that were added to the parent to improve efficiency, support persistence storage, allow interactive display, or satisfy programming practices.

> Principle 59: Queries Not States

Don't allow a child to object inherit read accessors for `state`, `type`, or `role` properties. Do allow the child to object inherit related property value services, such as `isPublished`, `isCancelled`, `isAdmin` etc.

# OBJECT INHERITANCE AND COLLABORATIONS

## Groups, Assemblies, and Containers

Object inheritance involves two objects representing one entity or event. If the parent is part of any groups, assemblies, or within any containers, the entity or event being represented is assumed to be part of them. Since the parent and child both represent the entity, the child object inherits the knowledge of the groups, assemblies, and containers.

EXAMPLE— In the e-commerce clothing store example, a product object inherits the categories (groups) of its parent product description

## Role Collaborations

Object inheritance in events happens when many entities of the same kind are interacting. Separate child events record the details of each interaction, while the parent records details that are common for all the entities interacting. When the parent event involves a role, that role is involved in the overall interaction, and therefore is object inherited by the child events.

## Place Collaborations

Events occur at a place. When a parent event occurs at a given place, it is usually the case that all of its child events happen at the same one. Therefore, this collaboration is object inherited by the child events.

## Event Transactions

If a parent is involved in events that pertain to all of its children, then those event collaborations are object inherited. Events that are not relevant for some or all of the parent’s children are not object inherited.

## Followed Transactions

If the parent is involved in events that imply follow-up events involving only one or some of the child objects, then those collaborations are not object inherited. This happens most often with line items. Composite transactions often have follow-up transactions that are also composite transactions. When this happens, the follow-up transactions only involve some of the line items in the original composite transaction. An object should only object inherit information that pertains to it, so line items should not object inherit follow-up transactions that do not involve them.

## Historical Transactions

A parent’s history affects its children. In other words, the entity that both the parent and child are representing is the real-world object whose history is being tracked. Therefore, if the parent has historical events, then those collaborations are object inherited.

EXAMPLE— An employee object inherits change of address events from its person. A product object inherits price changes from its parent.

## Parent Collaborations

If the parent is also a child with its own parent collaboration, then the grandchild object inherits the grandparent’s profile. All three objects are still representing one real-world object in more and more specific contexts or variations.

EXAMPLE— An airline allows its frequent flyers to become subscribers to its online service center, which allows them to enroll in forums and receive email updates about forum news. Since only frequent flyers can join, the online subscriber is a further role and child object of the frequent flyer. The online subscriber object inherits the parent collaboration from the frequent flyer so that object inheritable information in the person can be asked of the online subscriber.

> Principle 60: In My Parent’s Groups

Always allow a child to object inherit its parent’s group, assembly, and container collaborations.

> Principle 61: Remembering My Parent’s Events

Always allow a child to object inherit its parent’s historical and event transactions.

> Principle 62: Family Ties

Always allow a child to object inherit its parent’s parent, but do not allow a child to object inherit other child objects belonging to its parent.

> Principle 63: Share and Share Alike

Allow a child to object inherit follow-up transactions for its parent’s events if and only if the follow-up transactions are valid for all the parent’s children.

> Principle 64: My Parent the Event

Allow a line item child to object inherit the role and place collaborations of its composite transaction parent.

# OBJECT INHERITANCE AND SERVICES

## Determine Mine Services

Almost all determine mine services are object inherited because these are the services that satisfy requests for information and return readable properties and collaborations. The exceptions are those determine mine services that do not make sense for the child because they return aggregate information summarized from all the child objects, or they return information about only a few of the child objects. To decide if a determine mine is object inheritable, ask if every child object could be asked the question it answers. A service that aggregates information from all the child objects cannot be asked of a single child object, so it is not object inheritable. A service that involves filtering or ranking the child objects, such as finding the highest or lowest ranking child object, is not object inheritable either.

EXAMPLE— In the store example, a product object inherits all the read accessors for properties and collaborators from its parent product description. The product description service “get best-selling SKU,” which returns the best-selling specific product, is not object inherited.11 This service is not object inherited by the product objects because it is information about only one of the child objects. The service “is in category” is valid for all the child-specific products so it is object inherited.

## Analyze Transactions Services

A child object inherits an analyze transactions service if the child object inherits the transaction collaborators analyzed by the service. In other words, a child cannot object inherit information about transactions it cannot object inherit. Transaction collaborators are not object inheritable if the transactions do not apply to all the child objects. This happens with certain follow-up transactions.

EXAMPLE— A product manufacturer sets wholesale prices and distributor prices at the product description level. Wholesale prices are remembered through price history objects, while distributor prices are remembered as line items within distributor agreements. A product description calculates its average wholesale price and find its current distributors. A product object inherits these services from its product description.

## Conduct Business Services

Child objects do not object inherit conduct business services because child objects cannot do work that alters the state of the system beyond their own context, interaction, or variation. This eliminates all parent services that create new objects, alter properties, or change collaborations.

EXAMPLE— A hotel room type knows how to reserve itself. The reserve service creates a reservation for a customer and is not object inherited by the child hotel rooms. Write accessors for setting the smoking status, bed configuration, and special needs features of a hotel room type are not object inherited by the child hotel rooms. Collaboration services for adding and removing hotel rooms from the hotel room definition are not object inherited by the child hotel rooms.

> Principle 65: Determine Mine, Too

A determine mine service of a parent is object inheritable if every child object could be asked the question the determine mine service answers.

> Principle 66: Analyze Only What You Know

An analyze transactions service of a parent is object inheritable if the child object can object inherit the transactions being analyzed.

> Principle 67: Children Cannot Conduct Business

A conduct business service of a parent is never object inheritable because the child cannot alter the parent or the context of the parent.
