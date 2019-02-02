# Specific - TeamMember

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

## Team Member Initialization Rules

> A team member has the following initialization rules:

- A team member must have a person to exist.
- A team member has its initial security level set to "low"
- A team member has its initial role set to "member"

> Test objects

- A team member with chair role status and a high security level
- A team member with admin role status and default (low) security level
- A team member with member role status, default security, and no nominate privileges
- A team member with member role status, nominate privileges, and secret security level

## Define: Profile Interface

To specify object inheritance between a team member and its person, the team member profile interface extends the person profile interface.
The team member profile interface also describes the `get accessors` and `determine mine` services of team members.

```
interface ITeamMemberProfile extends IPersonProfile {
  // Accessors - get properties
  SecurityLevel getSecurityLevel();

  // Accessors - get property values
  boolean isRoleAdmin();
  boolean isRoleChair();
  boolean isRoleMember();

  // Accessors - get collaborators
  ITeam getTeam();
  IPerson getPerson();

  // Determine mine
  boolean hasDeletePrivilege();
  boolean hasNominatePrivilege();
}
```

## Define: Conduct Business Interface

The team member conduct business interface extends the team member profile interface and includes the set accessors and conduct business services particular to team members. Because a team member's privileges are coupled to the value of its role property, special conduct business services - makeAdmin, makeMember, and makeChair - set both properties together.
`Set accessors for the role property exist, but are not included in the conduct business interface because using them along could put a team member in an invalid state.`

```
interface ITeamMember extends ITeamMemberProfile {
  // Accessors - add collaborators
  void addPerson(IPerson aPerson) throws BusinessRuleException;

  // Conduct Business
  void makeAdmin() throws BusinessRuleException;
  void makeChair() throws BusinessRuleException;
  void makeMember() throws BusinessRuleException;
  void grantDeletePrivilege() throws BusinessRuleException;
  void grantNominatePrivilege() throws BusinessRuleException;
  void revokeDeletePrivilege() throws BusinessRuleException;
  void revokeNominatePrivilege() throws BusinessRuleException;
}
```

## Define: TeamMember Class

The TeamMember class has instance variables for its properties and its person collaborator.

> Utility class, SecurityLevel

It represents the security levels for team members. Later, we will see that a document also have a security level, and it plays a part in the collaboration rules that govern the document's nomination process. Since SecurityLevel must be `reused among several different domain classes`, it is a `standalone class` and not implemented as an inner class.

> Inner class,

The role property is implemented using inner class, TeamRole, whose objects have an integer code to efficiently represent, compare, and store the different role values and a string to cleanly print and display the role value. Three team role objects, representing the admin, chair, and member role values, are created and stored in static variables within the TeamMember class.

> TeamMember class specification

```
class TeamMember implements ITeamMember {
  // Define
  private IPerson person;
  private byte privileges;
  private TeamRole role;
  private SecurityLevel securityLevel;
}
```

> TeamRole inner class definition

```
private static class TeamRole {
  private int code;
  private String role;
  TeamRole(int roleCode, String roleString) {
    this.code = roleCode;
    this.role = roleString;
  }
  public int getCode() {
    return this.code;
  }

  @Override
  public String toString() {
    return this.role;
  }

  public boolean equals(Object anObject) {
    if(anObject instanceof TeamRole) {
      return (this.code == ((TeamRole)anObject).getCode());
    }
    else {
      return false;
    }
  }
}
```

## Initialize: TeamMember

Applying the initialization rules and the "Minimum Parameter" principle, TeamMember requires one constructor in Java; each takes only a single parameter. The following Java methods initialize the new object's properties before making the collaboration. Often, collaboration rules check property values, and if the properties are not yet initialized, then errors can occur.

The default constructor is not implemented in the Java version because the person collaboration is required for the parent - child relationship. The collaboration accessor for adding a person can throw a business rule exception; therefore, the constructor is declared to throw an exception.

> Principle 75: Properties Before Collaborators

Object construction methods initialize properties before establishing collaborations because collaboration rules may check property values.

```
// Initialize properties before establishing collaborations because collaboration rules may check property values
public TeamMember(IPerson person) throws BusinessRuleException {
  this.makeMember();
  this.securityLevel = new SecurityLevel();
  this.addPerson(person);
}
```

> TeamMember property accessors

```
// Accessors - get properties
@Override
public SecurityLevel getSecurityLevel() {
  return this.securityLevel;
}

// Accessors - get inherited properties
@Override
public String getName() {
  return this.person.getName();
}

@Override
public String getTitle() {
  return this.person.getTitle();
}

@Override
public String getEmail() {
  return this.person.getEmail();
}
```

## Accessing: Property Values

For the `inner private class TeamRole`, TeamMember has special get and set accessor services to protect values of these properties and encapsulate their implementations. The special get accessors check the properties for particular values, and the special set accessors assign the properties particular values.

The conduct business services that ensure that the proper privileges are assigned when the role property is set. Putting that code in the role set property value accessor would be doing too much work. For example, having the setRoleAdmin accessor also changing privileges would be creating a side-effect in addition to setting a property. To discourage use of the role property value set accessors, such as setRoleAdmin, we prefix them differently to distinguish them. Our convention for accessors that bypass business rules is to prefix them with `doSet`.

```
// Accessors - test for property values
@Override
public boolean isRoleAdmin() {
  return this.role.equals(ROLE_ADMIN);
}

@Override
public boolean isRoleChair() {
  return this.role.equals(ROLE_CHAIR);
}

@Override
public boolean isRoleMember() {
  return this.role.equals(ROLE_MEMBER);
}

@Override
public boolean hasNominatePrivilege() {
  return (this.privileges & PRIVILEGES_NOMINATE_MASK) > 0;
}

@Override
public boolean hasDeletePrivilege() {
  return (this.privileges & PRIVILEGES_DELETE_MASK) > 0;
}

// Accessors - set properties to special values
public void doSetRoleAdmin() {
  this.role = ROLE_ADMIN;
}

public void doSetRoleChair() {
  this.role = ROLE_CHAIR;
}

public void doSetRoleMember() {
  this.role = ROLE_MEMBER;
}

// Conduct Business
@Override
public void makeMember() throws BusinessRuleException {
  this.doSetRoleMember();
  this.privileges = PRIVILEGES_DEFAULT_MASK;
}

@Override
public void makeAdmin() throws BusinessRuleException {
  this.doSetRoleAdmin();
  this.grantNominatePrivilege();
  this.revokeDeletePrivilege();
}

@Override
public void makeChair() throws BusinessRuleException {
  this.doSetRoleChair();
  this.grantNominatePrivilege();
  this.grantDeletePrivilege();
}

// https://www.tutorialspoint.com/java/java_basic_operators.htm
// |= (OR)
@Override
public void grantNominatePrivilege() throws BusinessRuleException {
  this.privileges |= PRIVILEGES_NOMINATE_MASK;
}

@Override
public void grantDeletePrivilege() throws BusinessRuleException {
  this.privileges |= PRIVILEGES_DELETE_MASK;
}

// ^= (XOR)
@Override
public void revokeNominatePrivilege() throws BusinessRuleException {
  if(this.hasNominatePrivilege()) {
    this.privileges ^= PRIVILEGES_NOMINATE_MASK;
  }
}

@Override
public void revokeDeletePrivilege() throws BusinessRuleException {
  if(this.hasDeletePrivilege()) {
    this.privileges ^= PRIVILEGES_DELETE_MASK;
  }
}
```
