# Asynchronous Programming

> Future

Future is a handle for a value that isn't yet available but can be retrieved by invoking its get method after its
computation has finally terminated.
As a result the getPriceAsync method can return immdiately, giving the caller thread a chance to perform other useful
computations in the meantime.