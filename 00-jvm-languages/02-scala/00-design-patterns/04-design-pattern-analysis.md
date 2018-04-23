# Design Pattern Analysis Overview

## Process of Componentization

In order to fully componentize a pattern we must first be able to identify what varies from one instantiation of the pattern to the next. Our language must be able to abstract over this, or else there is no hope for writing a reusable pattern. Secondly, we must be able to modularize the things that does not vary from one instantiation to the next.

- Find what varies in this pattern
- abstract over it
- Find the things that does not vary
- modularize it
