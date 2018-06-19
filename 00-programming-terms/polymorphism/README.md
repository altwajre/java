# Polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/48ba1996-b2fb-4699-af96-0f76a0b7f6d7.xhtml

Polymorphism helps us to write generic code that can be reused and applied to a variety of types.

## Subtype polymorphism

Subtype polymorphism is expressed using inheritance with the extends keyword.

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/01b87664-0207-48f0-a50c-eedab26d548c.xhtml

We can use the abstract type and just call the pack method without thinking about what exactly it is.
Polymorphism will take care of printing the correct value.

## Parametric polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/f97ca94d-452a-4813-b121-f82c0ef76819.xhtml

Generics are parametric polymorphism

## Ad hoc polymorphism

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/0a26f810-f5ef-43da-a167-93806c33cdd8.xhtml

Ad-hoc polymorphism is similar to parametric polymorphism; however, in this case, the type of arguments is important, as the concrete implementation will depend on it.

- Ad-hoc polymorphism (Generics) is resolved at compile time
- Subtype polymorphism, which is done during runtime
