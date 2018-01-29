# Objects and Data Structures

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter06.html#ch6

## DATA ABSTRACTION

The interface represents a data structure.
The methods enforce an access policy.
You can read the individual coordinates independently, but you must set the coordinates together as an atomic operation.
Do not just blithely add getters and setters.

## DATA/OBJECT ANTI-SYMMETRY

Procedural code (code using data structures) makes it easy to add new functions without changing the existing data structures. OO code, on the other hand, makes it easy to add new classes without changing existing functions.

Procedural code makes it hard to add new data structures because all the functions must change. OO code makes it hard to add new functions because all the classes must change.

So, the things that are hard for OO are easy for procedures, and the things that are hard for procedures are easy for OO!

In any complex system there are going to be times when we want to add new data types rather than new functions. For these cases objects and OO are most appropriate.
On the other hand, there will also be times when we’ll want to add new functions as opposed to data types. In that case procedural code and data structures will be more appropriate.

### Procedural Shape

Change Geometry, the existing shape classes won't be affected.
Add a new shape class must change change Geometry.

### Object Oriented Polymorphic Shapes

Add a new shape, the existing shapes won't be affected.
Add a new function to shape interface, all of the shape classes must be changed.

### Law of Demeter

Talk to friends, not to strangers

- Each unit should have only limited knowledge about other units: only units "closely" related to the current unit.
- Each unit should only talk to its friends; don't talk to strangers.
- Only talk to your immediate friends.

### Train Wreck

`Train wreck` is violation of `Tell Don't Ask` and `Law of Demeter`

```
o.getX()
  .getY()
    .getZ()
      .doSomething();
```

### Hybrids

Avoid creating hybrids.
Half object and half data structure.
They have functions that do significant things, and they also have public variables or public accessors.
Hybrids make it hard to add new functions but also make it hard to add new data structures.

### Hiding Structure

Tell the object what to do, but not to ask the object for its state.
Objects are supposed to hide their internal structure, we should not be able to navigate through them.

## DATA TRANSFER OBJECTS

DTO - a class with public variables and no functions that converts raw data in a database into object in the application.

### Active Record

Active Records are special forms of DTOs that have navigational methods like save() and find().
Typically these Active Records are direct translations from database tables.
Don't treat these data structures as objects by putting business rule methods in them because it creates a hybrid between a data structure and an object.
Should treat Active Record as a data structure and to create separate objects that contain the business rules and that hide their internal data.
