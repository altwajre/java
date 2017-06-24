# 2.2. Structures

http://www.yaml.org/spec/1.2/spec.html#id2760395

YAML uses three dashes ("---") to separate directives from document content.
This also serves to signal the start of a document if no directives are present.
Three dots ("...") indicate the end of a document.

> Repeated nodes (objects)

Repeated nodes (objects) are first identified by an anchor (marked with the ampersand - "&")
and are then aliased (referenced with an asterisk - "*")

> Complex mapping

A question mark and space ("? ") indicate a complex mapping key. 
Within a block collection, ("key: value") pairs can start immediately following the dash, colon, or question mark.
