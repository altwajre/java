# Don't call us, we'll call you.

The Vert.x APIs are largely event driven. This means that when things happen in Vert.x that you are interested in,
Vert.x will call you by sending you events.

> Examples

1. a timer has fired
2. some data has arrived on a socket
3. some data has been read from disk
4. an exception has occurred
5. an HTTP server has received a request
