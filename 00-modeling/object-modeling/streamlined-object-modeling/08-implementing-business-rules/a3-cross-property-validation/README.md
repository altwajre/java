# Cross-Property Validation

Cross-property validation occurs when a property rule includes another property from the same or a different object.

`Example` - A team member cannot become a chair (i.e., set its role property to "chair") if the team member belongs to a team whose format prevents it from having another chair.

When two objects are involved in a cross-property validation rule, each object requires its own test service. The first test is in the object trying to change its property value, and the second test is in the object capable of vetoing the property value. Often, this veto property test doubles as a property collaboration rule. For example, a team member cannot change its role property value to "chair" if its team cannot support another chair; however, it is also true that a team member cannot be added to the team if the team cannot support another chair. Isolating this veto test into its own method allows its use for cross-property validation and for collaboration rule checking. The veto test is called from the test set method for the role property.

TeamMember setRoleChair scenario

> TeamMember

setRoleChair()
testSetRoleChair()
doSetRoleChair()

> Team

testCanBeChair()

I am a team member.
I set my role to chair by:
- testing if my role property can be set to the chair value
- if the test succeeds then I set my role property to the chair value

I test if my role property can set to chair by
- asking my team to test if I can be a chair on it.

The team object definition includes a method for counting the number of chair team members. In Java, this method uses an anonymous inner class that tests elements from a list, selects those returning true, and returns them in another list. The test used here is whether a team member is a chair.

`TeamMember.java`

```
// Accessors - set property value
public void doSetRoleChair() throws BusinessRuleException {
  this.testSetRoleChair();
  this.doSetRole(ROLE_CHAIR);
}

// Accessors - property value rules - Business rule
public void testSetRoleChair() throws BusinessRuleException {
  if(this.isRoleChair()) return;
  if(this.team != null) {
    this.team.testCanBeChair(this);
  }
}
```

`Team.java`

```
// Accessors - collaboration rules - Business rule
@Override
public void testCanBeChair(ITeamMember teamMember) throws BusinessRuleException {
  if(this.isFormatMultipleChair()) return;
  if(this.isFormatNoChair()) {
    throw new BusinessRuleException("Tried to add chair team member to no chairs team");
  }
  if(this.getChairs().size() > 0) {
    throw new BusinessRuleException("Tried to add another chair team member to single chair team");
  }
}

// Determine mine
@Override
public List getChairs() {
  CollectionSelector chairSelector = new CollectionSelector() {
    @Override
    public boolean selectBlock(Object listElement, Object keyValue) {
      return ((ITeamMember)listElement).isRoleChair();
    }
  };
  return (List)chairSelector.select(this.teamMembers);
}
```
