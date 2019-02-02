package model;

import util.BusinessRuleException;

public interface ITeamMember extends ITeamMemberProfile {
  // Accessors - add collaborators
  void addPerson(IPerson person) throws BusinessRuleException;
  void removePerson(IPerson person) throws BusinessRuleException;
  void addTeam(ITeam team) throws BusinessRuleException;
  void removeTeam(ITeam team) throws BusinessRuleException;

  // Accessors - do adds
  void doAddPerson(IPerson person);
  void doRemovePerson(IPerson person);
  void doAddTeam(ITeam team);
  void doRemoveTeam(ITeam team);
  void doAddNomination(INomination nomination);
  void doRemoveNomination(INomination nomination);

  // Conduct Business
  void makeAdmin() throws BusinessRuleException;
  void makeChair() throws BusinessRuleException;
  void makeMember() throws BusinessRuleException;
  void grantDeletePrivilege() throws BusinessRuleException;
  void grantNominatePrivilege() throws BusinessRuleException;
  void revokeDeletePrivilege() throws BusinessRuleException;
  void revokeNominatePrivilege() throws BusinessRuleException;
}
