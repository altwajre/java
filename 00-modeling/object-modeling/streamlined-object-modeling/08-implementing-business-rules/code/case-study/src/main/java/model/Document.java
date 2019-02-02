package model;

import util.BusinessRuleException;
import util.CollectionDetector;
import util.CollectionPerformer;

import java.text.DateFormat;
import java.util.*;

public class Document implements IDocument {
  // Define
  private String title;
  private Date publicationDate;
  private SecurityLevel securityLevel;
  private TreeSet nominations;

  // Initialize
  public Document(String title) throws BusinessRuleException {
    this.setTitle(title);
    this.publicationDate = null;
    this.securityLevel = new SecurityLevel();
    this.nominations = new TreeSet();
  }

  // Accessors - get properties
  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public SecurityLevel getSecurityLevel() {
    return this.securityLevel;
  }

  @Override
  public Date getPublicationDate() throws BusinessRuleException {
    if(this.publicationDate == null) {
      throw new BusinessRuleException("Document is unpublished");
    }
    else {
      return this.publicationDate;
    }
  }

  // Accessors - get collaborators
  @Override
  public List getNominations() {
    final ArrayList list = new ArrayList();
    CollectionPerformer performer = new CollectionPerformer() {
      @Override
      public void performBlock(Object listElement, Object keyValue) {
        list.add(listElement);
      }
    };
    performer.perform(this.nominations);
    return Collections.unmodifiableList(list);
  }

  /**
   * Return nominations iterator.
   * Allows subclasses to edit set.
   */
  protected Iterator getNominationsSet()
  {
    return this.nominations.iterator();
  }

  // Accessors - set properties

  @Override
  public void setTitle(String title) throws BusinessRuleException {
    // Logic rule
    if((title == null) || (title.length() == 0)) {
      throw new BusinessRuleException("Document cannot have null or empty title.");
    }

    this.testSetTitle(title); // Business rule
    this.doSetTitle(title); // Assign new value
  }

  // Property Rules: Business Rule
  public void testSetTitle(String title) throws BusinessRuleException {
    if(title.length() > 255) {
      throw new BusinessRuleException("Document title cannot be longer than 255 characters");
    }
  }

  // Accessors - do sets

  // Assign new value
  public void doSetTitle(String title) {
    this.title = title;
  }

  // Determine Mine
  @Override
  public boolean isPublished() {
    try {
      this.getPublicationDate();
    }
    catch (BusinessRuleException e) {
      return false;
    }
    return true;
  }

  @Override
  public boolean isApproved() {
    try {
      this.getApprovedNomination();
    }
    catch(BusinessRuleException e) {
      return false;
    }
    return true;
  }

  // Analyze Transactions
  @Override
  public INomination getApprovedNomination() throws BusinessRuleException {
    CollectionDetector approvedDector = new CollectionDetector() {
      @Override
      public boolean detectBlock(Object listElement, Object keyValue) {
        return false;
      }
    };
    INomination nomination = (INomination)approvedDector.detect(this.nominations);
    if(nomination == null) {
      throw new BusinessRuleException("Document has no approved nomination.");
    }
    return nomination;
  }

  // Conduct business
  @Override
  public void publish() throws BusinessRuleException {
    this.testSetPublicationDate();
    this.doSetPublicationDate(new Date());
  }

  // Accessors - property rules - Business rule
  public void testSetPublicationDate() throws BusinessRuleException {
    if(this.isPublished()) {
      throw new BusinessRuleException("Document already published");
    }
    if(!this.isApproved()) {
      throw new BusinessRuleException("Document not approved for publication");
    }
  }

  // Accessors - property do sets
  public void doSetPublicationDate(Date date) {
    this.publicationDate = date;
  }

  @Override
  public void nominate(ITeamMember teamMember) throws BusinessRuleException {
    this.createNomination(teamMember);
  }

  // Private
  private INomination createNomination(ITeamMember teamMember) throws BusinessRuleException {
    return new Nomination(teamMember, this);
  }

  // Collaboration Rules
  @Override
  public void testAddNomination(INomination nomination) throws BusinessRuleException {
    if(this.isPublished()) {
      throw new BusinessRuleException("Document already published");
    }
    INomination lastNomination = null;
    try {
      lastNomination = this.getLatestNomination();
    }
    catch (BusinessRuleException e) {
      // lastNomination is null. ok to nominate
      return;
    }
    if(lastNomination.isStatusPending() || lastNomination.isStatusInReview()) {
      throw new BusinessRuleException("Nomination denied. Document has unresolved nomination");
    }
  }

  // Return the latest nomination or throw exception if no nominations
  public INomination getLatestNomination() throws BusinessRuleException {
    if(this.nominations.isEmpty()) {
      throw new BusinessRuleException("Document has no nominations");
    }
    return (INomination)(this.nominations.first());
  }

  @Override
  public void testAddNominationConflict(INomination nomination, ITeamMember teamMember) throws BusinessRuleException {
    if(this.securityLevel.greaterThan(teamMember.getSecurityLevel())) {
      throw new BusinessRuleException("Security violation. Team member has improper security.");
    }
  }

  @Override
  public void doAddNomination(INomination nomination) {
    this.nominations.add(nomination);
  }

  @Override
  public void doRemoveNomination(INomination nomination) {
    this.nominations.remove(nomination);
  }

  @Override
  public String toString() {
    StringBuffer buffy = new StringBuffer(30);
    buffy.append("Document: " + this.title);
    if(this.isPublished()) {
      DateFormat dateFormat = DateFormat.getDateTimeInstance();
      System.out.println("published on " + dateFormat.format(this.publicationDate));
    }
    buffy.append("\n" + this.securityLevel);
    return buffy.toString();
  }

  @Override
  public boolean equals(Object document) {
    if(document instanceof Document) {
      Document other = (Document) document;
      return this.title.equals(other.title);
    }
    else {
      return false;
    }
  }

  // Testing
  public static Document testDocument() throws BusinessRuleException {
    Document document = new Document("1001 Ways to Build Object Models");
    return document;
  }

  public static Document testSecret() throws BusinessRuleException {
    Document document = new Document("Food and Beverage Industry Surveillance Tip");
    SecurityLevel securityLevel = document.getSecurityLevel();
    securityLevel.setLevelSecret();
    return document;
  }
}
