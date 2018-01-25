# Classes

https://www.safaribooksonline.com/library/view/clean-code/9780136083238/chapter10.html

## CLASS ORGANIZATION

> class Java convention

```
public static constants
private static constants
private instance variables
public function
private function
```

This follows the stepdown rule and helps the program read like a newspaper article.

### Encapsulation

Keep variables and utility functions private.
Sometimes need to make a variable or utility function protected so that it can be accessed by a test.

## CLASSES SHOULD BE SMALL!

Count responsibilities (public methods)

### The Single Responsibility Principle

A class or module should one, and one reason to change.

Getting software to work and making software clean are two very different activities.

We want our systems to be composed of many small classes, not a few large ones. Each small class encapsulates a single responsibility, has a single reason to change, and collaborates with a few others to achieve the desired system behaviors.

### Cohesion

Classes should have a small number of instance variables.
Each of the methods of a class should manipulate one or more of those variables.
In general the more variables a method manipulates the more cohesive that method is to its class.
A class in which each variable is used by each method is maximally cohesive.

### Maintaining Cohesion Results in Many Small Classes

When classes lose cohesion, split them.

## ORGANIZING FOR CHANGE

For most systems, change is continual.
Every change subjects us to the risk that the remainder of the system no longer works as intended.
In a clean system we organize our classes so as to reduce the risk of change.

### Isolating from change

Introduce interfaces and abstract classes to help isolate the impact of those details.
