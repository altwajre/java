# point-to-point

The sender can use either the default exchange or a direct exchange (that uses the routing key to determine to which queue a message must be sent;
the routing key should match the binding key between the exchange and the queue). The Receiver class can be used to subscribe to a particular
queue and receive messages from that queue.

## Run Apps

### sender
1, launch sender app
2, go to http://localhost:15672/#/queues a new `event_queue` Ready item added to the queue. if not, run it again until you see >= 1 Ready.

### receiver
1, launch receiver app
2, try to dequeue two items from http://localhost:15672/#/queues because there are two receivers

output:
/01-point-to-point/mylogs.log
```
16-03-2016 22:56:11 INFO  CompetingReceiver:39 - Message received: Test message.
16-03-2016 22:56:11 INFO  App:24 - receiver2: Test message.
```

