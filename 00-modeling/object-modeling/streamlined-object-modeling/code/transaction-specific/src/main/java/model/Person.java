package model;

import util.BusinessRuleException;
import util.CollectionDetector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/*
Business Rules

Person Initialization Rules

A person must have a name to exist.

 */
public class Person implements IPerson {

  // Define
  private String name;
  private String title;
  private EmailAddress email;
  private ArrayList teamMembers;

  /*
  Person must have valid name to be created
  Email and title area optional
   */
  public Person(String name) throws BusinessRuleException {
    this.setName(name);
    this.title = new String();
    this.email = new EmailAddress();
    this.teamMembers = new ArrayList();
  }

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

  // Determine Mine
  @Override
  public boolean hasValidEmail() {
    return this.email != null;
  }

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

  @Override
  public ITeamMember getTeamMember(ITeam team) {
    if(team == null) {
      return null;
    }

    CollectionDetector detector = new CollectionDetector() {
      public boolean detectBlock(Object listElement, Object keyValue) {
        ITeamMember teamMember = (ITeamMember) listElement;
        return teamMember.getTeam().equals(keyValue);
      }
    };

    return (ITeamMember)detector.detect(this.teamMembers, team);
  }

  // Accessors - add collaborators
  @Override
  public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
    if(teamMember == null) {
      throw new BusinessRuleException("Cannot add null team member.");
    }
    teamMember.addPerson(this);
  }

  @Override
  public void removeTeamMember(ITeamMember teamMember) throws BusinessRuleException {
    if(teamMember == null) {
      throw new BusinessRuleException("Cannot remove null team member.");
    }
    teamMember.removePerson(this);
  }

  @Override
  public void doAddTeamMember(ITeamMember teamMember) {
    this.teamMembers.add(teamMember);
  }

  @Override
  public void doRemoveTeamMember(ITeamMember teamMember) {
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

  // Test Object: pre-load with data
  public static Person testPerson() throws BusinessRuleException {
    Person person = new Person("Tom");
    person.setEmail("tom@gmail.com");
    person.setTitle("Manager");
    return person;
  }
}

