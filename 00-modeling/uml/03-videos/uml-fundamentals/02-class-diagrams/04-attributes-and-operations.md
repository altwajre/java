# Attributes And Operations

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214161

## Attributes

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
