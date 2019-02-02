# Team Member DIAPER (Updated)

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

## Team Member Initialization Rules

Because a team member represents a person's participation in a particular team,
a team member cannot exist without both a person and a team.
Team-Member thus requires an additional initialization rule: A team member must have a team to exist.

## Define: TeamMember Class

Give TeamMember an instance variable to reference its team collaborator.

Using the "How I See You" principle, the team instance variable is typed with the team conduct business interface.

> Principle 71: How I See You

Collaborators refer to one another using their conduct business interfaces.

```
class TeamMember implements ITeamMember {
  // Define
  private IPerson person;
  private ITeam team;
  private TeamRole role;
  private byte privileges;
  private SecurityLevel securityLevel;
}
```

## Initialize

The new initialization rule changes how team member objects are created. The team member object inherits some of its properties from its person. The team checks collaboration rules for the team member that may include these object inherited properties, so the collaboration between team `member and person` must be set before the `Team - TeamMember` collaboration is established.

Object creation methods that established two or more collaborations need some extra care. While the first collaboration may pass the rules and be established, the second could fail, in which case, the first must be dissolved, too.
In this example, the `team member must be removed form the person object` if `the team member cannot be a part of the team`.

> Principle 77: Putting Parents First

When an object must establish two or more collaborations to be valid, parent collaborations must be established first.

The team object is added to the parameter list of the TeamMember Java class constructor, and code is added to `break the person collaboration if the team collaboration fails`.

> Revised TeamMember constructor

```
// Initialize properties before establishing collaborations because collaboration rules may check property values
public TeamMember(IPerson person, ITeam team) throws BusinessRuleException {
  this.makeMember();
  this.securityLevel = new SecurityLevel();
  this.addPerson(person);
  try {
    this.addTeam(team);
  }
  catch(BusinessRuleException e) {
    person.removeTeamMember(this);
    throw e;
  }
}
```

## Accessing

The new collaboration requires new accessors to get, add, and remove the collaborator (Team).

```
public ITeam getTeam();
public void addTeam( ITeam aTeam) throws BusinessRuleException;
public void removeTeam( ITeam aTeam) throws BusinessRuleException;
```

## Printing

Applying the "Part Carries the Load" principle 76, a `team member` prints its own details, and asks its `team` to print its own details as well.

```
@Override
public String toString() {
  StringBuffer buffy = new StringBuffer(60);
  buffy.append("Team Member: ");
  buffy.append("\nRole: " + this.role);
  buffy.append("\n" + this.securityLevel);
  buffy.append("\n" + this.person);
  buffy.append("\n" + this.team);
  return buffy.toString();
}
```

## Equals

Applying the "Part Carries the Load" principle 76, a `team member` compares its `team` to another team member.

```
@Override
public boolean equals(Object teamMember) {
  if(teamMember instanceof TeamMember) {
    TeamMember other = (TeamMember) teamMember;
    if(!this.role.equals(other.role)) return false;
    if(this.person == null && (other.person != null)) return false;
    if(this.person != null && (!this.person.equals(other.person))) return false;
    if(this.team == null && (other.team != null)) return false;
    if(this.team != null && (!this.team.equals(other.team))) return false;
    return true;
  }
  return false;
}
```
