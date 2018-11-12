# Communication

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/75e07d65-7a09-42ba-a340-0a1504c09930.xhtml

## How it works

There is message object, which contains messages to be sent to the actors. Actors will use these messages for communication.
RandomNumberGeneratorActor receives the message GiveMeRandomNumber, and sends the result back to the sender as below
`sender ! Done(randomNumber)`
QueryActor sends the message to RandomNumberGenerator, and receives the result in case of Done.
 