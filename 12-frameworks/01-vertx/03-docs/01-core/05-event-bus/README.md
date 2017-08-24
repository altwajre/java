# The Event Bus

http://vertx.io/docs/vertx-core/java/#event_bus

There is a single event bus instance for every Vert.x instance and it is obtained using the method eventBus.

The event bus allows different parts of your application to communicate with each other in any language, and whether
they're in the same Vert.x instance, or in a different Vert.x instance.

## Messaging

> Publish/subscribe messaging

Messages are published to an address. Publishing means delivering the message to all handlers that are registered at
that address.

> Point to point and Request-Response messaging

Messages are sent to an address. Vert.x will then route it to just one of the handlers registered at that address.

If there is more than one handler registered at the address, one will be chosen using a non-strict round-robin algorithm.

With point to point messaging, an optional reply handler can be specified when sending the message.

When the reply is received back at the sender, it too can be replied to. This can be repeated adinfinitum, and allows a
dialog to be set-up between two different verticles.
