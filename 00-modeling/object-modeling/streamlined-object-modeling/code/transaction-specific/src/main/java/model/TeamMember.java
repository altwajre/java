package model;

import util.BusinessRuleException;
import util.CollectionSelector;

import java.util.*;

public class TeamMember implements ITeamMember {
  // Define
  private IPerson person;
  private ITeam team;
  public TeamRole role;
  private byte privileges;
  private ArrayList nominations;
  private SecurityLevel securityLevel;

  /*
  Little static inner class because Java lacks key-value utility class.
  Associates a string to a numeric code.
  Use string for nice display
  Use numeric code for quick comparisions
   */
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

    @Override
    public boolean equals(Object anObject) {
      if(anObject instanceof TeamRole) {
        return (this.code == ((TeamRole)anObject).getCode());
      }
      else {
        return false;
      }
    }
  }

  private static TeamRole ROLE_ADMIN = new TeamRole(0, "admin");
  private static TeamRole ROLE_CHAIR = new TeamRole(1, "chair");
  private static TeamRole ROLE_MEMBER = new TeamRole(2, "member");

  // Masks for checking privileges
  private static byte PRIVILEGES_DEFAULT_MASK = 0;
  private static byte PRIVILEGES_DELETE_MASK = 1;
  private static byte PRIVILEGES_NOMINATE_MASK = 2;

  // Number of documents can nominate per nomination time period
  private static int MAX_DOCUMENTS = 5;
  private static int MAX_CHAIR_DOCUMENTS = 10;

  // Number of days in nomination time period
  private static int NOMINATIONS_TIME_PERIOD = 30;

  // Initialize properties before establishing collaborations because collaboration rules may check property values
  public TeamMember(IPerson person, ITeam team) throws BusinessRuleException {
    this.makeMember();
    this.securityLevel = new SecurityLevel();
    this.nominations = new ArrayList();
    this.addPerson(person);
    try {
      this.addTeam(team);
    }
    catch(BusinessRuleException e) {
      person.removeTeamMember(this);
      throw e;
    }
  }

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

  @Override
  public boolean hasValidEmail() {
    return false;
  }

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

  public void doSetRole(TeamRole teamRole) {
    this.role = teamRole;
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
  public void testAddTeam(ITeam team) throws BusinessRuleException {
    if(this.team != null) {
      throw new BusinessRuleException("Team member already has a team.");
    }
    if(this.person != null) {
      this.testAddPersonTeamConflict(this.person, team);
    }
  }

  @Override
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

  @Override
  public void testRemoveTeam(ITeam team) throws BusinessRuleException {

  }

  @Override
  public void revokeDeletePrivilege() throws BusinessRuleException {
    if(this.hasDeletePrivilege()) {
      this.privileges ^= PRIVILEGES_DELETE_MASK;
    }
  }

  @Override
  public ITeam getTeam() {
    return this.team;
  }

  @Override
  public IPerson getPerson() {
    return null;
  }

  @Override
  public List getNominations() {
    return null;
  }

  // Accessors - add collaborators
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
  public void removePerson(IPerson person) throws BusinessRuleException {
    if(person == null) {
      throw new BusinessRuleException("Tried to remove null person");
    }
    this.doRemovePerson(person);
    person.doRemoveTeamMember(this);
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

  @Override
  public void removeTeam(ITeam team) throws BusinessRuleException {
    if(team == null) {
      throw new BusinessRuleException("Tried to remove null team");
    }
    this.doRemoveTeam(team);
    team.doRemoveTeamMember(this);
  }

  @Override
  public void doAddPerson(IPerson person) {
    this.person = person;
  }

  @Override
  public void doRemovePerson(IPerson person) {
    this.person = null;
  }

  @Override
  public void doAddTeam(ITeam team)
  {
    this.team = team;
  }

  @Override
  public void doRemoveTeam(ITeam team) {
    this.team = null;
  }

  @Override
  public void doAddNomination(INomination nomination) {
    this.nominations.add(nomination);
  }

  @Override
  public void doRemoveNomination(INomination nomination) {
    this.nominations.remove(nomination);
  }

  // Part carries the load principle - print team
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

  // Run
  public static TeamMember testTeamMember() throws BusinessRuleException {
    return TeamMember.testChair();
  }

  public static TeamMember testChair() throws BusinessRuleException {
    ITeam team = Team.testTeam();
    TeamMember teamMember = new TeamMember(Person.testPerson(), team);
    teamMember.makeChair();
    SecurityLevel level = teamMember.getSecurityLevel();
    level.setLevelHigh();
    return teamMember;
  }

  public static TeamMember testAdmin() throws BusinessRuleException {
    ITeam team = Team.testTeam();
    TeamMember teamMember = new TeamMember(Person.testPerson(), team);
    teamMember.makeAdmin();
    return teamMember;
  }
}

