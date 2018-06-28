# Flyweight

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/1ed9cb5c-5529-4e50-9cda-793f84586ba2.xhtml

Depending on the amount of shared data, sometimes the number of distinct shared objects could dramatically grow and not bring too much benefit. 
Moreover, it can complicate the factory and its usage. Multithreaded applications need extra care while working with factories. 
Last but not least, the developers need to be really careful while using shared objects, as any change in them could affect the entire application.
