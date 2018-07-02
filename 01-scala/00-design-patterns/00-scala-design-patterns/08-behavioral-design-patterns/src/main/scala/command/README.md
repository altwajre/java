# The command design pattern

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/310507b3-dfb7-4f29-8152-e94db0250935.xhtml

The purpose of the command design pattern is to encapsulate the information needed to perform an action at a later stage
and pass this information to the object that will be running the actual code.

- Command: the interface and its implementations that are being called by the invoker
- Receiver: the object knows how commands are executed.
- Invoker: it invokes the commands by calling their interface method.

> What it is not so good for

https://www.safaribooksonline.com/library/view/scala-design-patterns/9781788471305/66fd8b59-f4a9-4ab7-be71-a44716b2e2f7.xhtml

Even though the by-name parameter method looks nice and could make our writing shorter, it might not be a great idea here. 
A big disadvantage compared with our previous example is that we could actually plug any Unit data there, 
which could possibly not be relevant to what the receivers are supposed to do.
