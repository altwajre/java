# Collaboration Rules

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/ch04.html

## Object-Oriented Business Rules

Use the term "business rules" for constraints governing what actions and information are legal and valid within a business domain.

## Five Kinds of Collaboration Rules

> Type

Type rules limit the kinds of collaborators an object can have.

Example: you can't put a business (an object) in residential area (collaborator).

- Is the potential collaborator the right type for me?
- In entity collaborations, the most specific collaborator owns the rule
- In event collaborations, the interacting entity owns the rule

> Multiplicity

Multiplicity rules regulate the number of collaborators of an object.

Example: A system for delivering online interactive educational lectures limits the number of students (collaborator) that may enroll in each lecture (an object). Different lectures (objects) may have different limits depending on the material and lecturer.

- Do I have too many collaborations to establish another?
- Will I have too few collaborations if I remove one?
- Each collaborator checks its own multiplicity rules.

> Property

Property rules come in two variations: validation and comparison

1, A validation rule verifies a property value against a standard that is not dependent on the properties of the other potential collaborator.

Example: A customer (object) cannot place an order if he lacks a valid name, billing address, or credit card.

2, A comparison rule measures a property value of one object against one or more of the property values of the other potential collaborator.

Example: A perishable food product (object) cannot collaborate with a shipping container (collaborator) whose average temperature is outside the product's minimum and maximum temperature range

- Verify my property values or the potential collaborator's property values against a constant standard.
- The collaborator who knows the standard owns the rule
- Compare my property values with a potential collaborator's property values
- The collaborator who knows the acceptable range of values owns the rule

> State

State rules specify the proper states an object needs to be in to begin or end a collaboration.

Example: An order (object) cannot be shipped if it has been cancelled.

- Am I in the proper state for establishing or dissolving a collaboration?
- Each collaborator checks its won state rules

> Conflict

Conflict rules define the conditions under which current collaborators protest gaining a new collaborator or losing an existing one.

Example: A product cannot be added to an order if it conflicts with the order's customer, for example, the customer is under-age for the product.

- Do any of my collaborators conflict with the potential collaborator?
- Since conflict rules are just collaboration rules between indirect collaborators, the same principles for deciding who owns the collaboration rule apply

## Pattern Player Collaboration Rules

### Actor - Role Collaboration Rules

These collaboration rules test whether a `role` can be added or removed from an `actor`. 
