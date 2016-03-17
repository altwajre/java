# publish-subscribe

We can use a fanout exahnge and bind any number of queues to that exchange regardless of the binding key.
The Receiver class can be used to bind a specified queue to a fanout exchange and receive messages from it.

## Run Apps

### sender - the first time
1, launch sender app
2, go to http://localhost:15672/#/exchanges a new `pubsub_exchange` exchange is created.

### receiver
1, launch receiver app

### sender
1, launch sender app again to publish message

### receiver log file
/02-publish-subscribe/mylogs.log
```
16-03-2016 23:55:32 INFO  PublishSubscribeReceiver:42 - Message received: Test message.
16-03-2016 23:55:32 INFO  PublishSubscribeReceiver:42 - Message received: Test message.
16-03-2016 23:55:32 INFO  App:24 - receiver2: Test message.
16-03-2016 23:55:32 INFO  App:18 - receiver1: Test message.
```
