# Inheritance and Polymorphism

https://www.safaribooksonline.com/videos/java-object-oriented-programming/9781788296106/9781788296106-video5_2

> Inheritance

- The extends keyword is used to extend or subClass a Class
- The super keyword is used to access members of the superClass (the superClass and the subClass can have members with the same identifier)
- The invocation of the superClass Constructor must be the first line in the subClass constructor
- In object oriented programming the concept of "IS-A" is based on Class inheritance or Interface implementation
- "IS-A" is a way of saying "is a type of" so PassengerPlane IS-A AeroPlane
- Code wise the "IS-A" relation translate into instanceof

> Modifiers

- A Class marked abstract is intended to be extended (subClassed) it cannot be instantiated
- An abstract method does not have an implementation, it is intended to receive an implementation from a subClass
- A Class that has an abstract method must be declared abstract itself
- The first concrete subClass of an abstract Class must provide an implementation for all abstract members
- A method marked abstract cannot be static, private or final
- The final non-access modifier is used to prevent a method from being overridden or a Class from being extended (subClassed)
- A method is made final when the implementation it contains must not be changed by an eventual subClass
- A final Class cannot be extended with extends (subClassed)
- Class and methods cannot be marked both abstract and final
