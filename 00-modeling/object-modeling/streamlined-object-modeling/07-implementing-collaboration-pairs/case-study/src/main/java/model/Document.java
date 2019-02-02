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
    if((title == null) || (title.length() == 0)) {
      throw new BusinessRuleException("Document cannot have null or empty title.");
    }
    this.doSetTitle(title);
  }

  // Accessors - do sets

  public void doSetTitle(String title) {
    this.title = title;
  }

  public void doSetPublicationDate(Date date) {
    this.publicationDate = date;
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

  @Override
  public void publish() throws BusinessRuleException {

  }

  @Override
  public void nominate(ITeamMember teamMember) throws BusinessRuleException {

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
