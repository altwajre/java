# Whole - Team

https://learning.oreilly.com/library/view/streamlined-object-modeling/0130668397/ch07.html

This example continues the implementation of the domain we started in the previous section. Here we are implementing the group - member collaboration.

> Example

A team member belongs to exactly one team. Each team has a description and format, which can be one of the following: no chair, single chair, or multiple chairs.
The team's format defines the following collaboration rule: A team member cannot be added to a team if the team member has a chair role and the team's format cannot support a chair being added to the team.

In terms of implementation, the interesting aspects of the Team class are the format property and the collection of team member collaborators.

## Team: DIAPER

The format property is implemented like the role property of the TeamMember class, by encapsulating its representation inside the class, and providing special property value accessors. The team member collaboration is implemented similarly. The object definition has an instance variable for a collection object and accessors for adding and removing team members. A get accessor returns a view on the collection, and a special get accessor returns a single team member for a person, assuming the person is on the team.

### Define: Conduct Business Interface

Determine mine services are included in this conduct business interface because no profile interface is implemented.

### Define: Team Class

The Team class has an instance variable to hold the collection of team members, plus instance variables for the description and format properties.

TeamFormat is used to represent the three format values - no chair, single chair, and multiple chairs.

A static inner class `TeamFormat` that is similar with `TeamMember.TeamRole`

```
class Team implements ITeam {
  // Define
  private String description;
  private ArrayList teamMembers;
  private TeamFormat format;
}
```

### Initialize

Team Initialization code creates the collection object and sets the format to the default of "multiple chairs".

The initialization of the array list object and setting the format to the default occurs in the default constructor. No other constructors are needed.

```
public Team() {
  this.description = new String();
  this.teamMembers = new ArrayList();
  this.format = FORMAT_MULTIPLE;
}
```

### Accessing: Properties

The description instance variable has the usual get and set methods, returning and taking a string value, respectively. The format instance variable has get property value accessors of the form "isFormatX" for each X value of the format, and has set property value accessors of the form "setFormatX" for each X value of the format.

```
// Accessors - properties
@Override
public String getDescription() {
  return this.description;
}

// Accessing - get property values
@Override
public boolean isFormatNoChair() {
  return this.format.equals(FORMAT_NONE);
}

@Override
public boolean isFormatSingleChair() {
  return this.format.equals(FORMAT_SINGLE);
}

@Override
public boolean isFormatMultipleChair() {
  return this.equals(FORMAT_MULTIPLE);
}

// Accessing - set properties
@Override
public void setDescription(String newDescription) throws BusinessRuleException {
  this.description = newDescription;
}

// Accessing - set property values
@Override
public void setFormatNoChair() throws BusinessRuleException {
  this.format = FORMAT_NONE;
}

@Override
public void setFormatSingleChair() throws BusinessRuleException {
  this.format = FORMAT_SINGLE;
}

@Override
public void setFormatMutlipleChair() throws BusinessRuleException {
  this.format = FORMAT_MULTIPLE;
}
```

### Accessing Collaborations

The team has two get accessors for the team member collaboration: one returns a view containing all the team members, and the second returns a team member for a given person.

```
// Accessing - collaborators
// Returns a view
@Override
public List getTeamMembers() {
  return Collections.unmodifiableList(this.teamMembers);
}

@Override
public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Tried to add null team member");
  }
  teamMember.addTeam(this);
}

@Override
public void removeTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Tried to add null team member");
  }
  teamMember.removeTeam(this);
}

@Override
public void doAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  teamMembers.add(teamMember);
}

@Override
public void doRemoveTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  teamMembers.remove(teamMember);
}

@Override
public void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember.isRoleChair()) {
    this.testCanBeChair(teamMember);
  }
}

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

@Override
public ITeamMember getTeamMember(IPersonProfile person) {
  CollectionDetector personDetector = new CollectionDetector() {
    @Override
    public boolean detectBlock(Object listElement, Object keyValue) {
      return ((ITeamMember)listElement).getPerson().equals(keyValue);
    }
  };
  return (ITeamMember)personDetector.detect(this.teamMembers, person);
}
```

## Printing

Using the "Part Carries the Load" principle, a team does not ask its parts (TeamMember) to print. Instead, the team prints only its own properties.

```
@Override
public String toString() {
  StringBuffer buffy = new StringBuffer(30);
  buffy.append("Team: ");
  buffy.append("\nDescription: " + this.description);
  buffy.append("\nFormat: " + this.format);
  return buffy.toString();
}
```

## Equals

Using the "Part Carries the Load" principle, a team compares only its own properties and does not try to compare its team member

```
@Override
public boolean equals(Object team) {
  if(team instanceof Team) {
    Team other = (Team) team;
    if(!this.description.equals(other.description)) return false;
    if(!this.format.equals(other.format)) return false;
    return true;
  }
  else {
    return false;
  }
}
```
