package model;

import util.BusinessRuleException;
import util.CollectionDetector;
import util.CollectionSelector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team implements ITeam {
  // Define
  private String description;
  private ArrayList teamMembers;
  private TeamFormat format;

  private static class TeamFormat {
    private int code;
    private String format;

    TeamFormat(int formatCode, String formatString) {
      this.code = formatCode;
      this.format = formatString;
    }

    @Override
    public String toString() {
      return this.format;
    }

    @Override
    public boolean equals(Object teamFormat) {
      if(teamFormat instanceof TeamFormat) {
        return (this.code == ((TeamFormat) teamFormat).code);
      }
      else {
        return false;
      }
    }
  }

  private static TeamFormat FORMAT_NONE = new TeamFormat(2, "no chairs");
  private static TeamFormat FORMAT_SINGLE = new TeamFormat(1, "single chair");
  private static TeamFormat FORMAT_MULTIPLE = new TeamFormat(0, "multiple chairs");

  public Team() {
    this.description = new String();
    this.teamMembers = new ArrayList();
    this.format = FORMAT_MULTIPLE;
  }

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
    this.teamMembers.add(teamMember);
  }

  @Override
  public void doRemoveTeamMember(ITeamMember teamMember) throws BusinessRuleException {
    this.teamMembers.remove(teamMember);
  }

  @Override
  public void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {
    if(teamMember.isRoleChair()) {
      this.testCanBeChair(teamMember);
    }
  }

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

  // Using the "Part Carries the Load" principle, a team does not ask its parts (TeamMember) to print.
  // Instead, the team prints only its own properties.
  @Override
  public String toString() {
    StringBuffer buffy = new StringBuffer(30);
    buffy.append("Team: ");
    buffy.append("\nDescription: " + this.description);
    buffy.append("\nFormat: " + this.format);
    return buffy.toString();
  }

  // Using the "Part Carries the Load" principle, a team compares only its own properties
  // and does not try to compare its team members to those of the other team.
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

  // Run
  public static Team testSingleChairTeam() throws BusinessRuleException {
    Team team = new Team();
    team.setFormatSingleChair();
    team.setDescription("Executive Strategy Team");
    return team;
  }

  public static Team testTeam() throws BusinessRuleException {
    Team team = new Team();
    team.setDescription("System Integration Team");
    return team;
  }
}
