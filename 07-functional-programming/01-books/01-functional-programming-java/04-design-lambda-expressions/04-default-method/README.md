# default methods

## rules
- Subtypes automatically carry over the default methods from their supertypes
- For interfaces that contribute a default method, the implementation in a subtype takes precedence over the one in supertypes
- Implementations in classes, including abstract declarations, take precedence over all interface defaults
- If there's a conflict between two or more default method implementations, or there's a default-abstract conflict between
  two interfaces, the inheriting class should disambiguate.

