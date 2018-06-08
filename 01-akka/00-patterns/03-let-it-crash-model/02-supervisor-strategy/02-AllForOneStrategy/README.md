# Understanding AllForOneStrategy for actors

https://www.safaribooksonline.com/library/view/akka-cookbook/9781785288180/bbf200af-b954-4727-98c3-3dd8a8df2c4f.xhtml

AllForOneStrategy implies that in case of failure of any one actor under a supervisor, 
the strategy will be applied to all the children and grandchildren under supervision.

## How it works

We create two child actors, Calculator and ResultPrinter, under the supervision of the AllForOneStrategy Supervisor.
These child actors are closely related in the sense that the result of any operation carried out by Calculator are sent to ResultPrinter to print. 
When an exception occurs in Calculator, both the actors are restarted because of AllForOneStrategy.
