# request-reply

The sender will send a message to the default exchange with routing key that matches the name of the designated request queue.
The request receiver is a subscriber to the request queue.

## Run Apps

### sender
1, launch sender app
2, go to http://localhost:15672/#/queues new `request_queue` Ready item(s) added to the queue.

### receiver
1, launch receiver app

output:
/03-request-reply/mylogs.log
```
17-03-2016 10:34:00 INFO  RequestReceiver:40 - Request received: Test message.
```
