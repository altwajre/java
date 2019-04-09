# UML

lucidchart.com
draw.io

### Generalization and Specialization

- Inheritance in object-oriented systems
- Generalization/specialization notation
- Attributes and operations

> Inheritance in O-O Systems

- A key aspect of object-oriented languages is the ability to define classes that inherit features from another class
- The inheriting classes are known as subclasses or child classes
- The class being inherited from is known as the superclass or parent class
- Each subclass is a specialization of the superclass
- The superclass is a generalization of the subclasses
- Subclasses inherit attributes, operations and relationships from the superclass
- Subclasses typically extend the superclass with additional attributes, operations and associations
- Subclasses may also override the definition of operations in the superclass

> Notation

- A generalization specialization relationship is shown with an unfilled triangle at the superclass end of the relationship

- A subclass inherits attributes and operations, which can be shown with the name of the superclass


Device - superclass

```
- state: DeviceState
- name:  String
---
+ getState(): DeviceState
+ setState(DeviceState)
```

OpenClosedDevice - subclass

```

::Device
- state: DeviceState
- name:  String
---
+ setState(DeviceState) {redefines Class View::domain::Device.setState}
+ open()
+ close()
::Device
+ getState(): DeviceState
+ setState(DeviceState)
```
