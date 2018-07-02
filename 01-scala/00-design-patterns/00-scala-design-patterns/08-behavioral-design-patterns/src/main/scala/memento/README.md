# Memento

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/afdb9374-d4ab-4b3d-98a4-57a25eb15b36.xhtml

Developers should be careful when they use the memento design pattern. 
They should try to have the state saved in value objects if possible because if a mutable type is passed, 
it would be changed by reference and this will lead to unwanted results. 
Developers should also be careful about how far back in time they allow changes to be undoable because the more operations are saved in the stack, 
the more memory will be required. Finally, Scala is immutable and the memento design pattern does not always coincide with the language philosophy.
