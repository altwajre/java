# Example: Document Publication Date

This example shows a `state transition rule` for a descriptive property.

Example - A document cannot set its publication date if it does not have an approved nomination.

Publication date is a write-once, read-only property that gets set when the document is published. It serves double duty by indicating whether the document is published and, if so, when. These facts imply that the document does not have a property set accessor for the publication date, since this property cannot be externally edited.

- A test method to check if the publication date can be set
- An assignment method to put the value into the property
- A conduct business service that checks the property rule before assigning the publication date to the current date

```
// Conduct business method
@Override
public void publish() throws BusinessRuleException {
  this.testSetPublicationDate();
  this.doSetPublicationDate(new Date());
}

// Accessors - property rules - Business rule
public void testSetPublicationDate() throws BusinessRuleException {
  if(this.isPublished()) {
    throw new BusinessRuleException("Document already published");
  }
  if(!this.isApproved()) {
    throw new BusinessRuleException("Document not approved for publication");
  }
}

// Accessors - property do sets
public void doSetPublicationDate(Date date) {
  this.publicationDate = date;
}
```
