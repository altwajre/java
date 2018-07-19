# Observer

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/cb2a86a9-b698-4388-997d-e688605b497b.xhtml

In functional programming with Scala, one would possibly prefer using Akka and creating a publish-subscribe design instead. 
In the observer design pattern, object references are held in the observer's collection of the subject, 
which could cause memory leaks or unnecessary allocations during the lifetime of the application or the subject object. 
Finally, as with any other design pattern, the observer design pattern should be used only where necessary. 
Otherwise, we might end up complicating our application for no good reason.
