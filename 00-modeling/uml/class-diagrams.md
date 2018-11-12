# Class Diagrams

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214158

## Attributes

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

## Associations

> Multiplicity of association ends

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

## Composition and Aggregation

> Composition

- Composition is the strong whole-part relationship
- If a class is composed of others, deleting an instance of the whole results in deletion of the instances of the parts

> Aggregation

- Aggregation is the weaker whole-part relationship
- If a class is an aggregation of others, deleting instances of the whole leaves the instances of the parts untouched
