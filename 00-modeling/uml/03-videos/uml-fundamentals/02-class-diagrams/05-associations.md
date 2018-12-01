# Associations

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214163

- Association name and direction
- Direction of navigability
- Multiplicity of association ends
- Association roles

> Association Name and Direction

- The name of an association is shown next to the association
- A small arrow can be shown next to the name of show the direction in which it should be read

> Direction of Navigability

- An association end can have an arrow-head to indicate whether the association is navigable from the class

Schedule holds a reference of TimedEvent

[TimedEvent] <-- belongs to -- [Schedule]

> Multiplicity of Association Ends

- A label can ben shown at the end of an association to indicate the range of possible values for objects linked by the association
- The default value if there is no label is 1

[TimedEvent] 1..* <-- belongs to -- 1 [Schedule]

- Format of multiplicity labels

```
1           exactly one
0..1        zero or one (optional)
1..*        one or more
0..*        zero or more
*           same as 0..*
1..7        a specific range
1,3,5       a list of values
1,3,7..10   a list of values and ranges
```

> Association Roles

- Each end of an association has a role name, which defaults to the name of the class at that end with an initial lower case letter
- The name of the role can be set by the modeler
