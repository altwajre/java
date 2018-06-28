# The factory method

> What it is not so good for?

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/2a9630e3-2ad9-4d89-abdf-7494310822c7.xhtml

What happened in the preceding example is that we got a logical error, and nothing notifies us about this. 
When the number of methods to implement grows, this could become a problem and mistakes could be easily made. 
For example, our code didn't throw an exception, but this pitfall could lead to runtime errors that could be really hard to discover and debug.
