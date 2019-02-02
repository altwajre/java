package model;

import util.BusinessRuleException;

import java.util.ArrayList;
import java.util.List;

public class TeamMember implements ITeamMember {
  // Define
  private IPerson person;
  private ITeam team;
  private TeamRole role;
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
}

