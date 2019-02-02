# Example: Document Title

This example shows a `value limit rule` for a descriptive property

Example - A document cannot have a title longer than 255 characters.

Limiting the size of the document title is a business rule to be checked by a separate test method.
Checking if the title is null or empty is a logical rule and is kept inside the set property accessor.

```
@Override
public void setTitle(String title) throws BusinessRuleException {
  // Logic rule
  if((title == null) || (title.length() == 0)) {
    throw new BusinessRuleException("Document cannot have null or empty title.");
  }

  this.testSetTitle(title); // Business rule
  this.doSetTitle(title); // Assign new value
}

// Property Rules: Business Rule
public void testSetTitle(String title) throws BusinessRuleException {
  if(title.length() > 255) {
    throw new BusinessRuleException("Document title cannot be longer than 255 characters");
  }
}

// Assign new value
public void doSetTitle(String title) {
  this.title = title;
}
```
