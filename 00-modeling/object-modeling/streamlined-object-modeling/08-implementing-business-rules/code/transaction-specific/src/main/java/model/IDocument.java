package model;

import util.BusinessRuleException;

import java.util.Date;
import java.util.List;

public interface IDocument {
  // Accessors - get properties
  String getTitle();
  SecurityLevel getSecurityLevel();
  Date getPublicationDate() throws BusinessRuleException;

  // Accessors - get collaborators
  List getNominations();

  // Accessors - set properties
  void setTitle(String title) throws BusinessRuleException;

  // Determine Mine
  boolean isPublished();
  boolean isApproved();

  // Analyze Transactions
  INomination getApprovedNomination() throws BusinessRuleException;

  // Conduct Business
  void publish() throws BusinessRuleException;
  void nominate(ITeamMember teamMember) throws BusinessRuleException;

  // Collaboration Rules
  void testAddNomination(INomination nomination) throws BusinessRuleException;
  void testAddNominationConflict(INomination nomination, ITeamMember teamMember) throws BusinessRuleException;

  // Accessor - do adds & removes
  void doAddNomination(INomination nomination);
  void doRemoveNomination(INomination nomination);
}
