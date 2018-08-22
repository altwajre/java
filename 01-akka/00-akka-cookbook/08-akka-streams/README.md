# Akka Streams

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/dfba791e-5287-4b0e-bee8-c2c3d34b9441.xhtml

## Introduction

We live in a world where more and more things are getting connected to the Internet. 
These things generate helpful information that is usually delivered in real time. 
Streaming helps you ingest, process, analyze, and store data in a quick and responsive manner. 
Unlike batching, streaming happens in a real-time (or near real-time) fashion, 
which brings a new set of challenges-race conditions, network failures, buffers and so on.

Akka Stream is a module built on top of Akka to make the ingestion and processing of stream easier, using the actor model under the hood. 
It provides easy-to-use APIs to create streams that leverage the power of the Akka toolkit without explicitly defining actor behaviors and messages. 
This allows you to focus on logic and forget about all of the boilerplate code required to manage the actor. 
Akka Streams follows the Reactive Streams manifesto, which defines a standard for asynchronous stream processing.

Akka Streams has back-pressure mechanisms enabled by default. 
This feature allow components to signal other components in the stream that they are ready to process new elements. 
If some part of the processing takes longer (for example, an insertion into a database), we can throttle the consumer until things go back to normal.
