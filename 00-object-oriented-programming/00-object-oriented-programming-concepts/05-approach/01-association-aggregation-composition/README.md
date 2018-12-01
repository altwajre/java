# Association, Aggregation and Composition

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video5_1

> Composition

- Composition implies a parent-child relationship
- The child cannot exist independent of the parent (i.e. a Part Object is intended to compose a Vehicle Object)
- The lifecycles of the involved objects are the same. If one if garbage collected the other will also be garbage collected

> Aggregation

- Aggregation implies a parent child relationship
- The child can exist independently of the parent (i.e. a Car Object may belong to a CarPool Object)
- The lifecycles of each of the involved Objects are independent

> Association

- A form of dependency (a reference to an Object)
- Both objects have independent lifecycles
