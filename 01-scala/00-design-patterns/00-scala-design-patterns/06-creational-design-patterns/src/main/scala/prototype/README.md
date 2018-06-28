# Prototype

> What it is not so good for?

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/6ba5006b-7737-4126-bd86-b8d042cc1ed6.xhtml

Mistakes and side effects could be caused using shallow copies of objects, where the actual references point to the original instances. 
Also, avoiding constructors could lead to bad code. 
The prototype design pattern should be really used in cases where there might be a massive performance impact without it.
