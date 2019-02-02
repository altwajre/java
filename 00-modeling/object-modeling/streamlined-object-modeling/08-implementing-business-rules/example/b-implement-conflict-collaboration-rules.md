# Implementing Conflict Collaboration Rules

`Document management system requires a historical record of all nominations`
`No rollback because there is no remove collaboration rule`

Conflict rules come into play when business rules define restrictions between objects that collaborate through an intermediary object. In essence, conflict rules are collaboration rules between indirect collaborators, that is, in-laws. Conflict rules appear in all three of the fundamental patterns:

> Generic - Specific

Example: Business rules prohibiting conflicting `roles` from belongs to the same `actor`.

> Whole - Part

Example: Business rules preventing incompatible `parts` from joining the same `assembly`.

> Specific - Transaction

Example: Business rules preventing a `role` from participating in events at restricted location.

## Implementing Conflict Rules

Conflict rules are implemented by following the same principles used for other collaboration rules:

### Expand conduct business interfaces

```
public interface ITeamMember extends ITeamMemberProfile {
  void testAddNomination(INomination nomination) throws BusinessRuleException;
  void doAddNomination(INomination nomination);
}
```

```
public interface INomination extends Comparable {
  // Collaboration Rules
  void testAddDocument(IDocument document) throws BusinessRuleException;
  void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  // Accessors - do adds
  void doAddDocument(IDocument document);
  void doAddTeamMember(ITeamMember teamMember);
}
```

```
public interface IDocument {
  // Collaboration Rules
  void testAddNomination(INomination nomination) throws BusinessRuleException;
  void testAddNominationConflict(INomination nomination, ITeamMember teamMember) throws BusinessRuleException;

  // Accessor - do adds & removes
  void doAddNomination(INomination nomination);
  void doRemoveNomination(INomination nomination);
}
```

## Example: Team Member - Nomination - Document

The document method checking conflicts with a team member within a nomination. The conflict compares the security levels of the team member and documents.

> Document.java

```
public void testAddNominationConflict(INomination nomination, ITeamMember teamMember) throws BusinessRuleException {
  if(this.securityLevel.greaterThan(teamMember.getSecurityLevel())) {
    throw new BusinessRuleException("Security violation. Team member has improper security.");
  }
}
```

## Example: Nomination "Test" Method

As the direct of the conflict rules, the nomination is responsible for asking the document if it conflicts with the team member. To ensure commutativity, this check is included in both the `test add document` and `test add team member` methods, but it runs only when both `document` and `team member` collaborators are present. These "test" methods also enforce the nomination's multiplicity rules of having only one team member and one element.

> Nomination.java

```
public void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(this.teamMember != null) {
    throw new BusinessRuleException("Team member already exists.");
  }
  if(this.document != null) {
    this.document.testAddNominationConflict(this, teamMember);
  }
}

public void testAddDocument(IDocument document) throws BusinessRuleException {
  if (this.document != null) {
    throw new BusinessRuleException("Document already exists");
  }
  if(this.teamMember != null) {
    document.testAddNominationConflict(this, this.teamMember);
  }
}
```

## Example: Team Member "Test" Methods

The team member's "test" methods enforce the restrictions that a team member cannot nominate if its lacks the nominate privilege or if it has exceeded its maximum number of allowed nominations.

> TeamMember.java

```
public void testAddNomination(INomination nomination) throws BusinessRuleException {
  if(!this.hasNominatePrivilege()) {
    throw new BusinessRuleException("Security violation. Team member cannot nominate.");
  }
  if(this.countNominationsPerPeriod() >= this.maxNominationsAllowed()) {
    throw new BusinessRuleException("Team member cannot nominate. Too many nominations");
  }
}

// Determine mine
public int maxNominationsAllowed() {
  if(this.isRoleChair()) return MAX_CHAIR_DOCUMENTS;
  else return MAX_DOCUMENTS;
}

// Analyze transactions

public int countNominationsPerPeriod() {
  return this.countNominationsPerDays(NOMINATIONS_TIME_PERIOD);
}

public int countNominationsPerDays(int daysInPeriod) {
  if(this.nominations.isEmpty()) return 0;

  Calendar calendar = Calendar.getInstance();
  calendar.add(Calendar.DATE, -1 * daysInPeriod);
  Date endDate = calendar.getTime();

  CollectionSelector selectList = new CollectionSelector() {
    @Override
    public boolean selectBlock(Object listElement, Object keyValue) {
      return ((INomination)listElement).isAfter((Date)keyValue);
    }
  };
  Collection collection = selectList.select(this.nominations, endDate);
  return collection.size();
}
```

## Example: Document "Test" Methods

The document "test" methods enforce the restrictions that a document cannot be nominated if it lacks a title, is already published, or already has an unresolved nomination.

> Document.java

```
public void testAddNomination(INomination nomination) throws BusinessRuleException {
  if(this.isPublished()) {
    throw new BusinessRuleException("Document already published");
  }
  INomination lastNomination = null;
  try {
    lastNomination = this.getLatestNomination();
  }
  catch (BusinessRuleException e) {
    // lastNomination is null. ok to nominate
    return;
  }
  if(lastNomination.isStatusPending() || lastNomination.isStatusInReview()) {
    throw new BusinessRuleException("Nomination denied. Document has unresolved nomination");
  }
}

// Return the latest nomination or throw exception if no nominations
public INomination getLatestNomination() throws BusinessRuleException {
  if(this.nominations.isEmpty()) {
    throw new BusinessRuleException("Document has no nominations");
  }
  return (INomination)(this.nominations.first());
}
```

## Example: Nomination Collaboration Accessors

The nomination collaboration accessors direct the rule checking.

> Nomination.java

```
public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if (teamMember == null) {
    throw new BusinessRuleException("Tried to add nil team member");
  }
  this.testAddTeamMember(teamMember);
  teamMember.testAddNomination(this);
  this.doAddTeamMember(teamMember);
  teamMember.doAddNomination(this);
}

public void addDocument(IDocument document) throws BusinessRuleException {
  if(document == null) {
    throw new BusinessRuleException("Tried to add null document");
  }
  this.testAddDocument(document);
  document.testAddNomination(this);
  this.doAddDocument(document);
  document.doAddNomination(this);
}
```
