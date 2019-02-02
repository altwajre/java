# Generic - Person

https://www.safaribooksonline.com/library/view/streamlined-object-modeling/0130668397/ch07.html

`generic` (Person) with many `specifics` (TeamMembers)

## Business Rule Violations

Objects resist accepting an illegal property value, violating a collaboration rule, or performing a service while in an invalid state.

If business rule is broken, the appropriate response for one of these objects would be to stop processing and raise an exception describing the violation. Other object within the system can decide how to handle the business exception, by notifying a user, logging it in a report, or notifying some external system.

## Person Java DIAPER

DIAPER

- Define
- Initialize
- Access
- Print
- Equals
- Run

## Person Initialization Rules

> A person has the following initialization rule

A person must have a name to exist.

## Define: Person Profile Interface

The IPersonProfile interface is implemented by any class that has objects that assume a person profile.
Both person objects and team member objects exhibit this interface.

```
interface IPersonProfile {
  // Accessors - get properties
  String getName();
  String getTitle();
  String getEmail();

  // Determine Mine: answers requests for current information
  boolean hasValidEmail();
}
```

## Define: Person Conduct Business Interface

Person class implements the IPerson interface.

> IPerson consists following

- The methods that change the state of object by setting properties or adding collaborators.
- The collaboration accessors that are not object inherited.
The accessors to get the team members are included in the conduct business interface because child objects do not object inherit their parent's other children.
Collaborators refer to each other through their conduct business interfaces, so the collaboration accessors expect the team member conduct business interface as a parameter.

## Define: Person Class

To define the Person class, specify the superclass and interfaces implemented, and define the property and collaboration variables.

```
public class Person implements IPerson
{    
    // DEFINE
    private String name;
    private String title;
    private EmailAddress email;
    private ArrayList teamMembers;
}
```

## Initialize: Person Constructor

Initialization occurs when an object is created and sets the object's properties to default or given values.

> A person initialization rule

A person object cannot exist without a name.

- all constructors require a name parameter
- every constructor must validate the name.

```
// INITIALIZE
public Person(String newName) throws BusinessRuleException
{
    this.setName(newName);
    this.title = new String();
    this.email = new EmailAddress();
    this.teamMembers = new ArrayList();
}
```

## Accessing: Properties

Property accessors are public methods that get and set property values. The set accessors validate the new values and throw business rule exceptions when an illegal value is supplied.

```
// Accessors - get properties
@Override
public String getName() {
  return this.name;
}

@Override
public String getTitle() {
  return this.title;
}

@Override
public String getEmail() {
  return this.email.getAddress();
}

// Accessors - set properties
@Override
public void setName(String newName) throws BusinessRuleException {
  if((newName == null) || (newName.length() == 0)) {
    throw new BusinessRuleException("Person name cannot be null or empty.");
  }
  this.name = newName;
}

@Override
public void setTitle(String newTitle) throws BusinessRuleException {
  if(newTitle == null) {
    throw new BusinessRuleException("Person cannot have null title.");
  }
  this.title = newTitle;
}

@Override
public void setEmail(String newEmail) throws BusinessRuleException {
  this.email.setAddress(newEmail);
}

public void removeEmail() throws BusinessRuleException {
  this.email.setAddressEmpty();
}
```

## Accessing: Collaborations

Collaboration accessors add, remove, and get collaborators. A person has collaboration accessors for its team member collaborators.

> "unmodifiable" list

The getTeamMembers() returns the team member objects in a list that cannot be modified. Returning an "unmodifiable" list prevent attempts to add or remove team members without using the accessor methods and checking the collaboration rules. For subclasses that may legitimately need to ability to add and remove from the list, a protected accessor returns a list iterator on the array list. Using a list iterator gives subclasses edit abilities, but not the ability to reset the variable.

## Print

Following normal Java coding standards, an object prints its properties and collaborations in its toString method. Under the "Most Specific Carries the Load" principle, the person object, which is a generic, does not print its team member collaborator, which is a specific.

```
// Accessors - get collaborations
@Override
public List getTeamMembers() {
  return Collections.unmodifiableList(this.teamMembers);
}

// subclass can edit, but can not reset the variable
// https://www.youtube.com/watch?v=quRRkdsY13U
protected ListIterator getTeamMemberList() {
  return this.teamMembers.listIterator();
}

// Accessors - add collaborators
@Override
public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Cannot add null team member.");
  }
  this.teamMembers.add(teamMember);
}

@Override
public void removeTeamMember(ITeamMember teamMember) throws BusinessRuleException {
  if(teamMember == null) {
    throw new BusinessRuleException("Cannot remove null team member.");
  }
  this.teamMembers.remove(teamMember);
}

@Override
public String toString() {
  StringBuffer buffer = new StringBuffer(60);
  buffer.append("Person: ");
  buffer.append("\nName: " + this.name);
  buffer.append("\nTitle: " + this.title);
  buffer.append("\nEmail: " + this.email);
  return buffer.toString();
}
```

## Equals

Java coding standards, an object checks if it is equal to another object in its equals method, which takes a parameter of type Object.
The person, as a generic, does not compare its team member collaborator to that of the other person.

```
@Override
public boolean equals(Object person) {
  if(person instanceof Person) {
    Person other = (Person) person;
    if(!this.name.equals(other.name)) return false;
    if(!this.email.equals(other.email)) return false;
    if(!this.title.equals(other.title)) return false;
    return true;
  }
  return false;
}
```

## Run: Test Objects

Run services create objects in the class, pre-loaded with data for testing business services and collaboration rules. We implement services that create objects in the class as static methods.

```
public static Person testPerson() throws BusinessRuleException {
  Person person = new Person("Tom");
  person.setEmail("tom@gmail.com");
  person.setTitle("Manager");
  return person;
}
```

## Run: Test Case

A simple test case ensures the test object works, and exercises some of the accessors with business rules. Code that asks an object to perform a service that can potentially throw an exception must be wrapped in try-catch blocks to handle the exceptions.

```
class TestPerson {
  public static void main(String[] args) {
    Person person = null;

    try {
      person = Person.testPerson();
    } catch (BusinessRuleException e) {
      System.out.println("BOOM: " + e.getMessage());
    }

    System.out.println("\n" + person);
  }
}
```
