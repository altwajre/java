package model;

import util.BusinessRuleException;

import java.util.List;

public interface IPerson extends IPersonProfile {
  // Accessors - set properties
  void setName(String newName) throws BusinessRuleException;
  void setTitle(String newTitle) throws BusinessRuleException;
  void setEmail(String newEmail) throws BusinessRuleException;

  // Accessors - get collaborators
  List getTeamMembers();
  ITeamMember getTeamMember(ITeam team);

  // Accessors - add collaborators
  void addTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void removeTeamMember(ITeamMember teamMember) throws BusinessRuleException;

  // Accessors - do add collaborators
  void doAddTeamMember(ITeamMember teamMember);
  void doRemoveTeamMember(ITeamMember teamMember);
}

