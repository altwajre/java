# Groovy power assert as a replacement for Junit asserts

Spock tests
- lack of assert statements for evaluating results.

## Understanding how Groovy handles asserts

Groovy treats all objects as true unless
- The object is an empty string.
- The object is a null reference.
- The object is the zero number.
- The object is an empty collection (map, list, array, and so on)
- The object is the false Boolean
- the object is a regex matcher that fails.


