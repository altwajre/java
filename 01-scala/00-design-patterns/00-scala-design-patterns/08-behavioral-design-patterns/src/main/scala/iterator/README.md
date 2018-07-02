# Iterator

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/7dbca84f-b108-4492-b831-7b6572d591ef.xhtml

What would happen if another thread adds or removes objects to or from the original collection? 
Our iterator will not reflect that and it could lead to problems due to lack of synchronization. 
Making iterators capable of handling multithreaded environments is not a simple task.
