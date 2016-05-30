# Chapter 4. Building modular applications

## Module

A module is an independent, atomic unit of reuse.

A class can itself be a member of a larger unit of collective responsibilities called a module. A module is an
independent, contractually sound unit that is focused on a broad part of business responsibility. For example, a
persistence module may be responsible for storing and retrieving data from a database.

- Whole - A module is a complete unit of responsibility. With respect to an application, this means that modules can be
picked up and dropped in as needed.

- Independent - Unlike an object, a module does not have dependencies on other modules to perform its core function.
Apart from some common libraries, a module can be developed and tested independently.

- Contractually sound - A module conforms to well-defined behavior and can be relied on to behave as expected under all
circumstances.

- Separate - A module is not invasive of collaborators, and thus it is a discrete unit of functionality.

## Loose coupling with dependency injection

Loose coupling - makes testing, reuse, and evolution of components easy. This makes for modules that are easy to build
and maintain

Dependency injection - helps by keeping classes relatively free of infrastructure coe and by making it easy to assemble
objects in various combinations.

Keeping object graph structure described in one place also makes it easy to find and modify parts of it, with little
impact to core application code.

