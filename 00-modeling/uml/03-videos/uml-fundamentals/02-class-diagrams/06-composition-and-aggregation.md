# Composition And Aggregation

https://www.safaribooksonline.com/videos/uml-fundamentals/9781771373630/9781771373630-video214164

- Whole-part relationships
- Composition
- Aggregation

> Whole-part relationships

- In many systems, relationships between classes reflect a relationship between a whole and the parts that make it up:
  - Invoice and InvoiceLine
  - GUI and Button, TextBox, Label, etc.
  - FlightRoute and FlightLeg
  - Assembly and SubAssembly

- Strong relationship - deletion of the whole results in deletion of the parts
  - Invoice and InvoiceLine
- Weaker relationship - the parts continue to exist without the whole:
  - FlightRoute and FlightLeg  

> Composition

- Composition is the strong whole-part relationship
- If a class is composed of others, deleting an instance of the whole results in deletion of the instances of the parts
- 'Whole' end of the relationship shows a black diamond

[TimedEvent] 1..* <- is composed of - 1 [Schedule]

> Aggregation

- Aggregation is the weaker whole-part relationship
- If a class is an aggregation of others, deleting instances of the whole leaves the instances of the parts untouched
- 'Whole' end of the relationship shows an unfilled diamond
