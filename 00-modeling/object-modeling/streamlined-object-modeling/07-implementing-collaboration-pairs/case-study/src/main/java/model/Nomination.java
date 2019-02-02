package model;

import util.BusinessRuleException;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class Nomination implements INomination {
  // Define
  private String comments;
  private NominationStatus status;
  private Date nominationDate;
  private IDocument document;
  private ITeamMember teamMember;

  /*
  Little static inner class, because Java lacks key-value pair utility class
  Associates a string to a numeric code
  Use string for nice display
  Use numeric code for quick comparisons
   */
  private static class NominationStatus {
    private int code;
    private String status;

    NominationStatus(int statusCode, String statusString) {
      this.code = statusCode;
      this.status = statusString;
    }

    public int getCode() {
      return this.code;
    }

    @Override
    public String toString() {
      return this.status;
    }

    @Override
    public boolean equals(Object status) {
      if (status instanceof NominationStatus) {
        return this.code == ((NominationStatus) status).getCode();
      } else {
        return false;
      }
    }
  }

  /*
  Four states of a nomination
  Starts life in the pending state
  Can move from "pending" to "in review" and back again
  Can move from "in review" to "approved" or "rejected"
  Cannot move from "pending" to "approved" or "rejected"
  Cannot move from "approved" to "rejected", "pending", or "in review"
  Cannot move from "rejected" to "approved", "pending", or "in review"
   */
  private static NominationStatus STATUS_PENDING = new NominationStatus(0, "pending");
  private static NominationStatus STATUS_IN_REVIEW = new NominationStatus(1, "in review");
  private static NominationStatus STATUS_REJECTED = new NominationStatus(2, "rejected");
  private static NominationStatus STATUS_APPROVED = new NominationStatus(3, "approved");

  // Initialize
  /*
  Create a new nomination for a document by a team member.
  Adds team member first. If adding document causes exception, then remove the team member,
  and throw the original exception
   */
  public Nomination(ITeamMember teamMember, IDocument document) throws BusinessRuleException {
    this.status = STATUS_PENDING;
    this.nominationDate = new Date();
    this.comments = new String();
    this.doAddTeamMember(teamMember);
    try {
      this.addDocument(document);
    } catch (BusinessRuleException e) {
      teamMember.doRemoveNomination(this);
      throw e;
    }
  }

  @Override
  public String getComments() {
    return null;
  }

  @Override
  public Date getNominationDate() {
    return null;
  }

  @Override
  public boolean isStatusApproved() {
    return false;
  }

  @Override
  public boolean isStatusRejected() {
    return false;
  }

  @Override
  public boolean isStatusPending() {
    return false;
  }

  @Override
  public boolean isStatusInReview() {
    return false;
  }

  @Override
  public boolean notResolved() {
    return false;
  }

  @Override
  public IDocument getDocument() {
    return null;
  }

  @Override
  public ITeamMember getTeamMember() {
    return null;
  }

  @Override
  public void setComments(String comments) throws BusinessRuleException {

  }

  @Override
  public void setStatusPending() throws BusinessRuleException {

  }

  @Override
  public void setStatusInReview() throws BusinessRuleException {

  }

  @Override
  public void setStatusApproved() throws BusinessRuleException {

  }

  @Override
  public void setStatusRejected() throws BusinessRuleException {

  }

  // Accessors - add collaborators

  @Override
  public void addTeamMember(ITeamMember teamMember) throws BusinessRuleException {
    if (teamMember == null) {
      throw new BusinessRuleException("Tried to add nil team member");
    }

  }

  @Override
  public void addDocument(IDocument document) throws BusinessRuleException {

  }

  @Override
  public void doAddDocument(IDocument document) {

  }

  @Override
  public void doAddTeamMember(ITeamMember teamMember) {

  }

  // Determine Mine
  @Override
  public boolean isBefore(Date date) {
    return false;
  }

  @Override
  public boolean isAfter(Date date) {
    return false;
  }

  // Collaboration Rules
  @Override
  public void testAddDocument(IDocument document) throws BusinessRuleException {
    if (this.document != null) {
      throw new BusinessRuleException("Document already exists");
    }
  }

  @Override
  public void testAddTeamMember(ITeamMember teamMember) throws BusinessRuleException {

  }

  @Override
  public int compareTo(Object o) {
    return this.compareTo((Nomination)o);
  }

  /*
  Compare against another nomination
  This ordering allows sort to have most recent first.
  @return -1 if this nomination is more recent than a nomination
  @return  0 if this nomination is on same date as a nomination
  @return  1 if this nomination is before a nomination
   */
  public int compareTo(Nomination nomination) {
    return nomination.nominationDate.compareTo(this.nominationDate);
  }

  @Override
  public String toString() {
    DateFormat dateFormat = DateFormat.getDateTimeInstance();
    StringBuffer buffy = new StringBuffer(30);
    buffy.append("Nomination on: ");
    buffy.append(dateFormat.format(this.nominationDate));
    buffy.append("\nStatus: " + this.status);
    buffy.append("\n" + this.document);
    buffy.append("\n" + this.teamMember);
    return buffy.toString();
  }

  @Override
  public boolean equals(Object nomination) {
    if (nomination instanceof Nomination) {
      Nomination other = (Nomination) nomination;
      if (!this.status.equals(other.status)) return false;
      if (!this.nominationDate.equals(other.nominationDate)) return false;
      if (this.document == null && (other.document != null)) return false;
      if (this.document != null && (!this.document.equals(other.document))) return false;
      if (this.teamMember == null && (other.teamMember != null)) return false;
      if (!this.teamMember.equals(other.teamMember)) return false;
      return true;
    } else {
      return false;
    }
  }

  // Run
  public static Nomination testNomination() throws BusinessRuleException {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -35);
    ITeamMember teamMember = TeamMember.testTeamMember();
    IDocument document = Document.testDocument();
    Nomination nomination = new Nomination(teamMember, document);
    nomination.nominationDate = calendar.getTime();
    return nomination;
  }
}
