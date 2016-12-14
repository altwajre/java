# identifying dependencies for injection - combinatorial bindings

We’ve already seen that a service generally has some kind of identify, whether an arbitrary string identifier, the class it belongs to, or some other combinatorial key.

## Identify components and dependencies
A dependency is some implementation of a service. It may only be a flat object with no dependencies of its own. Or it may be an object with a vast graph of interconnected dependencies, which themselves have dependencies, and so on.

A dependency is any particular permutation of objects in a graph that represents the original service; that is, all permutations obey the same contract.
Permutations of the same service may be thought of as variants (or implementations) of that service.

### Keys provide a binding between a label and the object graph that implements the service it identifies.

If you wanted to use one particular implementation instead of another, you’d only need to point the dependent to its key. Keys are also essential when we want to leverage other features of dependency injectors:
Interception - Modifying an object’s behavior
Scope - managing an object’s state
Lifecycle - notifying an object of significant events

