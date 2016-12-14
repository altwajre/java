# Chapter 5. Scope: a fresh breath of state

## Singleton scope
A singleton's context is the injector itself. The life of a singleton is tied to the life of the injector.
Therefore, only one instance of a singleton is ever created per injector.

### Identify which service should be a singleton
If a service represents a conceptual nexus for clients, then it is likely candidate. For instance, a data connection
pool is a central port of call for any service that wants to connect to a database. Thus, connection pools are good
candidates for singleton scoping.

services that are stateless (in other words, objects that have no dependencies or whose dependencies are immutable) are
good candidates. Since there is no state to manage, so scoping and singleton scoping are both equally viable options.
In such cases, the singleton scope has advantages over no scope for a number of reasons:
 - Objects can be created at startup (sometimes called eager instantiation), saving on construction and wiring overhead.
 - There is a single point of reference when stepping through a debugger.
 - Maintaining the lone instance saves on memory.
Business objects are perfect candidates for singleton scoping. They hold no state but typically require data-access
dependencies. These services are sometimes referred to as managers or simply business APIs, as in PersonnelManager,
SalaryManager, and StockOptionsService in hypothetical employee payroll system. Any services APIs are likewise good
candidates to be stateless singletons.

Another oft-stated use case for singletons is when dealing with any object graphs that are expensive to construct and
assemble. This may be due to any of the following reasons:
 - The object graph itself being very large
 - Reliance on slow external resources (like network storage)
 - Some difficult computation performed after construction

Singleton scoping allows many instances of the same class to exist and be used by dependents; it allows only one shared
instance of a key. This is quite different from the conventional understanding of singletons.