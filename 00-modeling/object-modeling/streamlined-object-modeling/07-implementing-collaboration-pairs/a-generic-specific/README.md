# GENERIC – SPECIFIC OBJECT DEFINITIONS

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

## generic - specific collaboration pattern

- actor - role
- item - specific item
- composite transaction - line item

### Defining Objects: DIAPER

Creating Object Definitions Steps

> Define

Define the structure of the objects created by this definition:

- Name - Given the class a name
- Heritage - Indicate superclass
- Protocol - Indicate interfaces exhibited
- Properties - Define variables for object model and design properties
- Collaboration - Define variables for single and collective collaborations

> Initialize

Write code to initialize each newly created object

- Properties - Set to default or initial values
- Collaborations - Create collections for collective collaborations

> Access

Write code to access each object's properties

- Properties - Write get and set accessors
- Collaborations - Write get, add, and remove accessors

> Print

Write code to return a textual description of each object

- Properties - Describe values of select properties
- Collaborations - Ask select collaborators to describe themselves

> Equals

Write code to test for equality

- Properties - Write code to test for equality
- Collaborations - Compare select collaborators

> Run

Write code to create objects with good values for testing

- Properties - Generate good values for testing
- Collaborations - Use test objects from select collaborator classes

## Generic - Specific Object Definitions

> generic - specific

- actor - role
- item - specific item
- composite transaction - line item

### Specifying Object Inheritance

Specify the properties and services in the `generic` that are accessible from the `specific` object.

### Define: The Parent Profile Interface

In object-speak, the construct for specifying expected behavior is an "interface".
When an object definition is associated with an interface, the object definition must implement a matching method for each method signature in the interface.

### Define: The Conduct Business Interface

The Person `conduct business interface` extends the Person `profile`

`interface IPerson extends IPersonProfile`

### Define: The Child Profile Interface

> Principle 70: Conduct Business Interfaces

A conduct business interface includes all the business services of an object, either directly or by extending the object's profile interface

> Principle 71: How I See You

Collaborators refer to one another using their conduct business interfaces

### Initialize: Initialization Rules

A type of object cannot exist without certain data or a certain collaborator.

Rules specifying information an object needs to exist are called `initialization rules`.

Example - A person cannot exist without a valid name. A videotape cannot exist without its video title, which is also its parent object.

### Initialize: Object Construction

An object construction method can only accept given values for the properties and collaborators mentioned in the `initialization rules`.

> Principle 73: Minimum Parameter Rule

Only properties and collaborations necessary for an object to exist should be passed into the object's construction method.

### Access: Object Definition Variables

Objects require storage to hold their properties and collaborations, and they get their storage allocations from the variables specified within their object definitions.

Example: actor - role - A domain allows a person to take on employee, customer, and broker roles. A person can have at most one employee role, one active customer role, and any number of broker roles. The person object definition includes separate collaboration variables for each role type. The employee variable, if set, references a single employee object. The customer variable references a collection of customer object, but only one can be active. The broker variable references a collection of broker objects. Each role includes a collaboration variable that references its person object.

### Access: Collaborator Accessors

Collaborator accessors add, remove, and get collaborators. All generics add and remove specifics with the following accessors:

add (aSpecific) Adds designated specific to generic if collaboration rules allow.
remove (aSpecific) Removes designated specific if collaboration rules allow.

A generic with a single specific has the following accessor:

get() Returns specific, if it exists.

A generic with multiple specifics has the following accessors: The second one is optional, and used as needed by the domain.

get() Returns collection of specifics
get(criteria) Returns particular specific that satisfies the criteria, if it exists.

A specific has the following accessor methods

add (aGeneric) Adds designated generic to specific if collaboration roles allow.
remove (aGeneric) Removes designated generic from specific if collaboration rules allow:
get() Returns generic

### Print: Describing Objects

Print methods are used primarily for debugging and testing.
A useful print method returns a description of an object in terms of its properties and collaborators.

- Put work in the most specific collaborator. The specific has the most knowledge and so putting the work there places it closer to the data.
- A generic should print its own properties, but should not ask its specific to print itself.
- A specific should print its own properties, and ask its generic to print itself.

> Principle 74: Most Specific Carries the Load

When work requires cooperation between two collaborators, encapsulate the majority of the effort within the most specific collaborator.

### Equals: Detecting Duplicate Objects

Object models have collaboration rules that prohibit duplicate objects. Most systems don't want to add an object to a collection twice, or at least want smart processing to handle it when it happens. The trick is having a way to compare the object being added against the ones already there.

- Two persons with the same Social Security Number may be considered equal.
- Good object think dictates that an object can determine for itself if it is equal to another object. Within the equals method, the object doing the work compares its properties and collaborators against those of the other object.
- Let the specific do the work
- When a generic compares itself to another generic, their specifics don't enter into it.
- However for a specific, the generic holds a significant portion of its state. So when a specific compares itself to another specific, the generics must also be compared.

### Run: Test Objects

Test objects are objects populated with realistic property values, and are associated with collaborators with realistic data.

Test Objects provide following:
- Quick tests of the object infrastructure
- Building blocks for growing the system incrementally
- Instructive examples for understanding the object

- Like unit testing that allows testing even when test databases are not available.
- Linked test objects together according to the collaboration patterns in the object model. Connecting collaborators together allows testing of the collaboration rules and non-trivial business services.
- Define a generic test object with realistic property values but without a Specific collaborator; define a specific test object with realistic property and a generic test object collaborator.

### Generic - Specific Template

- `object definitions` as `classes`
- `generalization class` as `superclass`
- `specialization class` as `subclass`
- `services` as `methods`
