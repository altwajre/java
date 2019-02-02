# Document (Specific)

Interesting implementation aspects of the document are the security level, publication date properties, and the collection of nominations. We have seen the security level before. This is the same security level object used in the TeamMember class implementation. Every document is created with an initial security level of "low"

Publication date is only set if the document is published; otherwise, the date is null. When the state of the object determines whether a property exists or is accessible, it is a business rule violation to request the property when the object is not in the proper state. Accordingly, asking an unpublished document for its publication date is a business rule violation, and so the get accessor throws an exception.

Use a collection to hold collaborators. This collection will be searched to find the approved nomination and totally the number of nominations within a range of dates. A document has an analyze transactions service to return its approved nomination, because while the document may have any number of pending, in review, and rejected nominations, it can only have one approved nomination. Also, once a document has an approved nomination, its behavior changes. However, asking an unapproved document for its approved nomination is similar to asking an unpublished document for its publication date; it generates a business rule violation.

A document has the following initialization rules:

- A document must have a title property to exist
- A document by default has its security level set to "low"

## Define: Conduct Business Interface

This interface is fairly straightforward. Because there is no profile interface, the conduct business interface has the accessors, determine mine services, and analyze transactions service. It also provides the conduct business services to publish and nominate a document.

```
public interface IDocument {
  // Accessors - get properties
  String getTitle();
  SecurityLevel getSecurityLevel();
  Date getPublicationDate() throws BusinessRuleException;

  // Accessors - get collaborators
  List getNominations();

  // Accessors - set properties
  void setTitle(String title) throws BusinessRuleException;

  // Determine Mine
  boolean isPublished();
  boolean isApproved();

  // Analyze Transactions
  INomination getApprovedNomination() throws BusinessRuleException;

  // Conduct Business
  void publish() throws BusinessRuleException;
  void nominate(ITeamMember teamMember) throws BusinessRuleException;
}
```

## Define: Document Class

Keeps the nominations sorted by storing them in a TreeSet, which is a standard Java class that exhibits the SortedSet interface. A TreeSet sorts itself in ascending order with the lowest ordered elements first.

```
public class Document implements IDocument {
  // Define
  private String title;
  private Date publicationDate;
  private SecurityLevel securityLevel;
  private TreeSet nominations;
}
```

## Initialize

Document initialization code creates the collection object, sets the security level to the default, sets the string property to an empty string, and sets the publication date to null. A string title is required for the document to exist, and the set title accessor throws an exception if the title is null.

```
public Document(String title) throws BusinessRuleException {
  this.setTitle(title);
  this.publicationDate = null;
  this.securityLevel = new SecurityLevel();
  this.nominations = new TreeSet();
}
```

## Accessing: properties

The usual suspects apply here. The publication date has some interesting accessors. The get accessor throws a business exception if the property is not set. To avoid this unhappy event, an additional property value accessor tests if the publication date is set.

```
public Date getPublicationDate() throws BusinessRuleException
{
  if (this.publicationDate == null)
    throw new BusinessRuleException("Document is unpublished.");
  else return this.publicationDate;
}

public boolean isPublished()
{
  try {this.getPublicationDate();}
  catch(BusinessRuleException ex){return false;}
  return true;
}
```

## Printing and Equals

Printing and comparison (equals) are implemented using the "Let the Coordinator Direct" principle: the `specific (document)` prints and compares its own properties, but not the `transaction (nomination)`
