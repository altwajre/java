# Supervision and Monitoring

https://www.safaribooksonline.com/library/view/learning-akka/9781784391836/video2_5.html

> Supervision

- Describes a dependency relationship between actors
- Parent delegates tasks to children
- Parent takes the decision when failure happens
  - Resume the child
  - Restart the child
  - Stop the child
  - Propagate error message to parent by throwing exception

> Supervision Strategies in Akka

- OneForOneStrategy
- OneForAllStrategy

> Monitoring

- Monitoring is thus used to tie one actor to another, so that it may react to the other actor's termination, in contrast to Supervision which reacts to failure.
