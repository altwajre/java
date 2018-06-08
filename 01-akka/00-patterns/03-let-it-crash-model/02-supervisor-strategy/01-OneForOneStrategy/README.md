# Understanding OneForOneStrategy for actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/23857519-5b09-4561-9618-83acde22f12c.xhtml

## How it works

We create two actors, IntAdder and Printer, under the same supervisor.
Since the supervisor specifies OneForOneStrategy for its children, we see that only the child that fails is restarted by the supervisor, not the others.
