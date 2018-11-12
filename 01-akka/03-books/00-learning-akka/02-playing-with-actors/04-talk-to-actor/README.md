# Talking to an Actor

https://www.safaribooksonline.com/library/view/learning-akka/9781784391836/video2_4.html

- Tell vs Ask
- Implementing the use case that represents Tell and Ask

## Send Messages

> Messages are sent to an actor through one of the following methods

- Tell(!) - fire and forget. send message async, and returns immediately, not interested in result.
- Ask(?) - send message async, returns a future with possible reply
