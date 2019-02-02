package model;

import util.BusinessRuleException;

import java.util.Date;

public interface INomination extends Comparable {
  // Accessors - get properties
  String getComments();
  Date getNominationDate();

  // Accessors - get property values
  boolean isStatusApproved();
  boolean isStatusRejected();
  boolean isStatusPending();
  boolean isStatusInReview();
  boolean notResolved();

  // Accessors - get collaborators
  IDocument getDocument();
  ITeamMember getTeamMember();

  // Accessors - set properties
  void setComments(String comments) throws BusinessRuleException;

  // Accessors - set property values
  void setStatusPending() throws BusinessRuleException;
  void setStatusInReview() throws BusinessRuleException;
  void setStatusApproved() throws BusinessRuleException;
  void setStatusRejected() throws BusinessRuleException;

  // Accessors - add collaborators
  void addTeamMember(ITeamMember teamMember) throws BusinessRuleException;
  void addDocument(IDocument document) throws BusinessRuleException;

  // Accessors - do adds
  void doAddDocument(IDocument document);
  void doAddTeamMember(ITeamMember teamMember);

  // Determine Mine
  boolean isBefore(Date date);
  boolean isAfter(Date date);

  // Collaboration Rules
  void testAddDocument(IDocument document) throws BusinessRuleException;
  void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException;
}
