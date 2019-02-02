# Nomination (Transaction)

A nomination goes through various lifecycle states:

- Pending
- In review
- Approved
- Rejected

A nomination's lifecycle state transitions are governed by business rules.
A nomination cannot:

- Transition to the "pending" state unless in review or already pending
- Transition to the "in review" state unless pending or already in review
- Transition to the "approved" state unless in review or already approved
- Transition to the "rejected" state unless in review or already rejected

These rules are enforced in the property value accessor methods.

The nomination has the following initialization rules:

- A nomination must have a document to exist
- A nomination must have a team member to exist
- A nomination has a nomination date set to the date and time when the nomination was created

## Define: Nomination Class

A Date object is used to represent the nomination date property, and an inner class is defined to encapsulate the representation of the status property.
Both collaborator variables, `document` and `team member`, are referenced by their conduct business interfaces.

```
public class Nomination implements INomination {
  // Define
  private String comments;
  private NominationStatus status;
  private Date nominationDate;
  private IDocument document;
  private ITeamMember teamMember;
}
```

## Initialize

A nomination requires two objects to exist. The implementation pattern here is similar to creating `team members`: if the second collaboration fails, then the first one must be dissolved.

```
public Nomination(ITeamMember teamMember, IDocument document) throws BusinessRuleException {
  this.status = STATUS_PENDING;
  this.nominationDate = new Date();
  this.comments = new String();
  this.doAddTeamMember(teamMember);
  try {
    this.addDocument(document);
  }
  catch (BusinessRuleException e) {
    teamMember.doRemoveNomination(this);
    throw e;
  }
}
```

## Accessing: Properties and Collaborations

Accessors include the regular get accessors, and a set accessor for the comments property. There are also `special property value accessors` for the status, isStatusApproved, setStatusApproved, etc. These follow the conventions used to define the property value accessors for the team member role property. One difference is that these set property value accessors check the `state transition rules` and throw an exception if any rule is violated.

## Printing

Applying the "Let the Coordinator Direct" principle, a nomination prints its own properties, and also directs its collaborators to print themselves.

```
@Override
public String toString() {
  DateFormat dateFormat = DateFormat.getDateTimeInstance();
  StringBuffer buffy = new StringBuffer(30);
  buffy.append("Nomination on: ");
  buffy.append(dateFormat.format(this.nominationDate));
  buffy.append("\nStatus: " + this.status);
  buffy.append("\n" + this.document);
  buffy.append("\n" + this.teamMember);
  return buffy.toString();
}
```

## Equals

Applying the "Let the Coordinator Direct" principle, a nomination compares its own properties, plus it directs its collaborators to compare themselves.

```
@Override
public boolean equals(Object nomination) {
  if (nomination instanceof Nomination) {
    Nomination other = (Nomination) nomination;
    if (!this.status.equals(other.status)) return false;
    if (!this.nominationDate.equals(other.nominationDate)) return false;
    if (this.document == null && (other.document != null)) return false;
    if (this.document != null && (!this.document.equals(other.document))) return false;
    if (this.teamMember == null && (other.teamMember != null)) return false;
    if (!this.teamMember.equals(other.teamMember)) return false;
    return true;
  } else {
    return false;
  }
}
```
