# Object Identity

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_1

## Identity vs Equality

> The == operator applied to Objects

- The == operator is the identity operator
- Access to Objects is done through references and two references are equal according to the == operator when referencing the same Object (they then have the same identity)

> The equals() method

- The equals() method is inherited from the Object Class and is intended for developers to override and define a meaningful equality
- When a Class does not override the equals() method it behaves like the == operator

## Value Objects

Define meaningful equality for a variable (not a class)

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_2

### Meaningful equality for variables

- As mentioned previously by overriding the equals() method a developer defines a meaningful equality at the Class level
- How do we define meaningful equality for a variable
- The solution is to wrap the variable as an instance variable in a Class and provide an equals() method for that Class
- Objects created from that Class are referred to as "value Objects" because they are used simply to wrap/embed a specific value


### Pros and cons

> Benefits:

- The code gets more expressive
- Code is safer (Java is strongly typed)
- Flexibility in terms of internal representation
- Facilitates code maintenance

> Drawbacks:

- More classes are created
- More memory consumed

## hashCode() and equals()

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video4_3

- equals() used in a map
- hashCode(), HashMap and HashSet
- equals() and hashCode() in a HashMap
- The contract for equals() and hashCode()

### equals() used in a Map

> Client Policy

Iterate client to find match

Client | Policy
C 1    | P 55
C 12   | P 24
C 7    | P 13

### hashcode(), HashMap and HashSet

- Hashcodes are used to increase performance of some collection Classes. In those Classes a hashcode
  - Is associated to an Object
  - Designates a group of Objects as more Object are associated to it
  
- The HashMap and HashSet classes use the hashcode value of an Object as an "instruction" on where to
  - Store the object in an "add" operation
  - Locate the object in a "search" operation 

HashMap<Client, Policy> myHashMap = new HashMap<Client, Policy>();

### The Contract for equals() and hashCode()

| Condition                    | Required                     | Allowed             |
|------------------------------|------------------------------|---------------------|
| x.equals(y) == true          | x.hashCode() == y.hashCode() |                     |
| x.hashCode() != y.hashCode() | x.equals(y) == false         |                     |
| x.equals(y) == false         |                              | No requirement      |
| x.hashCode() == y.hashCode() |                              | x.equals(y) == true |
