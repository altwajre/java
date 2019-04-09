# Class Diagrams

https://www.lucidchart.com

Attributes, Operations and Relationship

## Relationship - Associations, Line, Arrow

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214165

> Associations - Direction of Navigability

- An association end can have an `arrow-head` to indicate whether the association is navigable from the class

Schedule holds a reference of TimedEvent

[TimedEvent] <-- belongs to -- [Schedule]

> Generalization and Specialization - Subclass

- A generalization/specialization relationship is shown with an `unfilled triangle` at the superclass end of the relationship

[Builder] <|--- [ConcreteBuilder]

### Composition and Aggregation

> Whole-part relationships

- In many systems, relationships between classes reflect a relationship between a whole and the parts that make it up:

> Composition

- Composition is the strong whole-part relationship
- If a class is composed of others, deleting an instance of the whole results in deletion of the instances of the parts
- 'Whole' end of the relationship shows a `black filled diamond`

[TimedEvent] 1..* <- is composed of - 1 [Schedule]

> Aggregation

- Aggregation is the weaker whole-part relationship
- If a class is an aggregation of others, deleting instances of the whole leaves the instances of the parts untouched
- 'Whole' end of the relationship shows an `unfilled diamond`

## Attributes

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214161

- Attributes are shown in the first compartment under the name of the class
- Attribute names use camel case

> Visibility

```
+ public
- private
# protected
~ package
```

> Type

```
- name: String
- startDate: Date
- endDate: Date
- enabled: boolean
- device: Device
- events: TimedEvent [1..*]
```

> Initial Value

```
- name: String
- startDate: Date = today
- endDate: Date
- enabled: boolean = true
- device: Device
- events: TimedEvent [1..*]
```

## Operations

- Operations are shown in the second compartment under the attributes


> Visibility

```
+ addTimedEvent()
# addTimedEvents()
- getTimedEvent()
~ removeTimedEvent()
```

- Return Values

```
+ addTimedEvent()
+ addTimedEvents(): void
+ getTimedEvent(): TimedEvent
+ removeTimedEvent()
```

- Parameters

```
+ addTimedEvent(event: TimedEvent)
+ addTimedEvents(events: TimedEvent[1..*]): void
+ getTimedEvent(): TimedEvent
+ removeTimedEvent(pos: int)
+ removeTimedEvent(event: TimedEvent)
+ changeDates(startDate: Date, duration: int)
```
