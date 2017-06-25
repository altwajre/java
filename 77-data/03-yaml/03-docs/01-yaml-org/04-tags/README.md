# Tags

http://www.yaml.org/spec/1.2/spec.html#id2761292

In YAML, untagged nodes are given a type depending on the application.
Use the seq, map and str types from the fail safe schema.
Use the int, float, and null types from the JSON schema.
The repository includes additional types binary, omap, set and others.

Explicit typing is denoted with a tag using the exclamation point ("!") symbol.
Global tags are URIs and may be specified in a tag shorthand notation using a handle.
Application-specific local tags may also be used.
