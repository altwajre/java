# State, Role, and Type Property Business Rules

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch08.html

State, role, and type properties have fixed sets of legal values that are supplied by domain experts. Such properties with fixed sets of values are also known as enumerated types, and require special property accessors to encapsulate their implementation. In the previous chapter, these accessors were called property value accessors.

`Example` - A nomination with a status property has get accessors that ask if the status has a particular value: isStatusPending, isStatusInReview, isStatusApproved, isStatusRejected. It also has set accessors that request the status to take on a particular value: setStatusPending, setStatusInReview, setStatusApproved, setStatusRejected.

For some enumerated type properties, the current property value can prohibit the property from taking on other values. This happens frequently with lifecycle state properties; the current state value restricts the next acceptable state value.

`Example` - A nomination with a lifecycle state of "rejected" cannot transition to a lifecycle state of "pending", "in review", or "approved"

Principle 83: Enumerated Property Business Rules

Properties with enumerated types are governed by business rules that define the set of legal values and the legal transactions from one value to another.

## Example - Nomination Status

`Example` - A nomination has a status property with the following possible values:
- Pending
- In review
- Approved
- Rejected

A nomination's lifecycle state transitions are governed by business rules. A nomination cannot:
- Transition to the "pending" status unless in review or already pending
- Transition to the "in review" state unless pending or already in review
- Transition to the "approved" state unless in review or already approved
- Transition to the "rejected" state unless in review or already rejected

Each lifecycle state transition rule is implemented by a distinct method. This provides more flexibility for specialization classes that may want to selectively alter the transition rules.

```
// Accessors - set property values
@Override
public void setStatusRejected() throws BusinessRuleException {
  this.testSetStatusRejected();
  this.doSetStatus(STATUS_REJECTED);
}

// Accessors - property value rules - Business rule
public void testSetStatusRejected() throws BusinessRuleException {
  if(this.isStatusInReview() || this.isStatusRejected()) return;
  else {
    throw new BusinessRuleException("Nomination cannot be rejected. Not under review");
  }
}

// Accessors - property do sets
public void doSetStatus(NominationStatus status) {
  this.status = status;
}
```
