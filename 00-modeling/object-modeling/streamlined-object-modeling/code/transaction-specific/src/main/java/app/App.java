package app;

import model.*;
import util.BusinessRuleException;

public class App {
  public static void main(String[] args) throws BusinessRuleException {
    Person person = new Person("Tom");
    person.setEmail("tom@gmail.com");
    person.setTitle("QA Lead");

    Team team = new Team();
    team.setDescription("eComm Team");
    System.out.println(team.format + " - team.format");

    TeamMember teamMember = new TeamMember(person, team);
    teamMember.makeAdmin();
    System.out.println(teamMember.role + "       - teamMember.role");

    Document document = new Document("Java");

    document.nominate(teamMember);
    System.out.println(document.nominations.size() + "       - document.nominations");
    INomination latestNomination = document.getLatestNomination();
    System.out.println(((Nomination)latestNomination).status + "   - latestNomination.status");
    latestNomination.setStatusInReview();
    System.out.println(((Nomination)latestNomination).status + " - latestNomination.status");
    latestNomination.setStatusApproved();
    System.out.println(((Nomination)latestNomination).status + "  - latestNomination.status");
    System.out.println(document.isApproved() + "    - document.isApproved()");
    document.publish();
    System.out.println(document.isPublished() + "    - document.isPublished()");

    System.out.println("\n# Transaction - Nomination");
    System.out.println(latestNomination);

    System.out.println("\n# Role - TeamMember");
    System.out.println(teamMember);

    System.out.println("\n# SpecificItem - Document");
    System.out.println(document);
  }
}
/*
multiple chairs - team.format
admin       - teamMember.role
1       - document.nominations
pending   - latestNomination.status
in review - latestNomination.status
approved  - latestNomination.status
true    - document.isApproved()
true    - document.isPublished()

# Transaction - Nomination
Nomination on: Jan 21, 2019 3:40:34 PM
Status: approved
Document: Java
Published on Jan 21, 2019 3:40:34 PM
Security Level: low [level=0]
Team Member:
Role: admin
Security Level: low [level=0]
Person:
Name: Tom
Title: QA Lead
Email: tom@gmail.com
Team:
Description: eComm Team
Format: multiple chairs

# Role - TeamMember
Team Member:
Role: admin
Security Level: low [level=0]
Person:
Name: Tom
Title: QA Lead
Email: tom@gmail.com
Team:
Description: eComm Team
Format: multiple chairs

# SpecificItem - Document
Document: Java
Published on Jan 21, 2019 3:40:34 PM
Security Level: low [level=0]
 */
