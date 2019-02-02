# Implementing Collaboration Rules Example

## Expand Conduct Business Profiles

Update the person, team member, and team conduct business interfaces to include the "do" and "test" methods for collaborations.

> Conduct business interfaces updated with collaboration "test" and "do" methods

### Conduct business interfaces updated with collaboration rule test methods

```
public interface IPerson extends IPersonProfile {
  // Accessors - collaboration do adds
  void doAddTeamMember(ITeamMember teamMember);
  void doRemoveTeamMember(ITeamMember teamMember);
}
```

```
public interface ITeamMember extends ITeamMemberProfile {
  // Accessors - collaboration rules - business rules
  void testAddPerson(IPerson person) throws BusinessRuleException;
  void testAddTeam(ITeam team) throws BusinessRuleException;
  void testRemovePerson(IPerson person) throws BusinessRuleException;
  void testRemoveTeam(ITeam team) throws BusinessRuleException;

  // Accessors -  collaboration do adds
  void doAddPerson(IPerson person);
  void doRemovePerson(IPerson person);
  void doAddTeam(ITeam team);
  void doRemoveTeam(ITeam team);
}
```

```
public interface ITeam {
  // Accessors -  collaboration do adds
  void doAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void doRemoveTeamMember(ITeamMember teamMember) throws BusinessRuleException;

  // Accessors - collaboration Rules - business rules
  void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void testCanBeChair(ITeamMember teamMember) throws BusinessRuleException;
}
```

## Example: Person - Team Member - Team Collaboration Accessors

Both person and team delegate to team member to direct the establishment or dissolution of the collaboration.

### "test" methods for checking collaboration rules - business rules

> TeamMember.java

```
@Override
public void testAddPerson(IPerson person) throws BusinessRuleException {
  if(this.person != null) {
    throw new BusinessRuleException("Team member already a person.");
  }
  if(!person.hasValidEmail()) {
    throw new BusinessRuleException("Person cannot be team member. Invalid email.");
  }
  if(this.team != null) {
    this.testAddPersonTeamConflict(person, this.team);
  }
}

public void testAddPersonTeamConflict(IPerson person, ITeam team) throws BusinessRuleException {
  ITeamMember teamMember = team.getTeamMember(person);
  if(teamMember != null) {
    throw new BusinessRuleException("Tried to add person twice to team.");
  }
}

@Override
public void testRemovePerson(IPerson person) throws BusinessRuleException {
  if(person == null) {
    throw new BusinessRuleException("Tried to remove null person.");
  }
  if(!person.equals(this.person)) {
    throw new BusinessRuleException("Tried to remove different person.");
  }
  if(this.team != null) {
    throw new BusinessRuleException("Team member on team cannot remove person.");
  }
}
```

> Team.java

```
@Override
public void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember.isRoleChair()) {
    this.testCanBeChair(teamMember);
  }
}
```

## low-level methods for connecting and disconnecting collaborators

> Person.java

```
@Override
public void doAddTeamMember(ITeamMember teamMember) {
  this.teamMembers.add(teamMember);
}

@Override
public void doRemoveTeamMember(ITeamMember teamMember) {
  this.teamMembers.remove(teamMember);
}
```

> TeamMember.java

```
@Override
public void doAddPerson(IPerson person) {
  this.person = person;
}

@Override
public void doRemovePerson(IPerson person) {
  this.person = null;
}
```

> Team.java

```
@Override
public void doAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  this.teamMembers.add(teamMember);
}

@Override
public void doRemoveTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  this.teamMembers.remove(teamMember);
}
```

## Collaboration add accessors with collaboration rule testing

> Person.java

```
@Override
public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Cannot add null team member.");
  }
  teamMember.addPerson(this);
}
```

> TeamMember.java

```
@Override
public void addPerson(IPerson person) throws BusinessRuleException {
  if(person == null) {
    throw new BusinessRuleException("Tried to add null person");
  }
  this.testAddPerson(person);
  this.doAddPerson(person);
  person.doAddTeamMember(this);
}

@Override
public void addTeam(ITeam team) throws BusinessRuleException {
  if(team == null) {
    throw new BusinessRuleException("Tried to add null team");
  }
  this.testAddTeam(team);
  team.testAddTeamMember(this);
  this.doAddTeam(team);
  team.doAddTeamMember(this);
}
```

> Team.java

```
@Override
public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Tried to add null team member");
  }
  teamMember.addTeam(this);
}
```
