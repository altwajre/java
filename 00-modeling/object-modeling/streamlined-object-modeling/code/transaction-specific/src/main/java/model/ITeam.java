package model;

import util.BusinessRuleException;

import java.util.List;

public interface ITeam {
  // Accessing - properties
  String getDescription();
  void setDescription(String newDescription) throws BusinessRuleException;

  // Accessing - property values
  boolean isFormatNoChair();
  boolean isFormatSingleChair();
  boolean isFormatMultipleChair();

  void setFormatNoChair() throws BusinessRuleException;
  void setFormatSingleChair() throws BusinessRuleException;
  void setFormatMutlipleChair() throws BusinessRuleException;

  // Accessing - collaborations
  List getTeamMembers();

  void addTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void removeTeamMember(ITeamMember teamMember) throws BusinessRuleException;

  void doAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void doRemoveTeamMember(ITeamMember teamMember) throws BusinessRuleException;

  // Collaboration Rules
  void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void testCanBeChair(ITeamMember teamMember) throws BusinessRuleException;

  // Determine Mine
  List getChairs();
  ITeamMember getTeamMember(IPersonProfile person);
}
