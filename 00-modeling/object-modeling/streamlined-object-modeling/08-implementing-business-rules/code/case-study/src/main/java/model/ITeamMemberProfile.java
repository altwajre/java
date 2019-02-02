package model;

import java.util.List;

public interface ITeamMemberProfile extends IPersonProfile {
  // Accessors - get properties
  SecurityLevel getSecurityLevel();

  // Accessors - get property values
  boolean isRoleAdmin();
  boolean isRoleChair();
  boolean isRoleMember();

  // Accessors - get collaborators
  ITeam getTeam();
  IPerson getPerson();
  List getNominations();

  // Determine mine
  boolean hasDeletePrivilege();
  boolean hasNominatePrivilege();
}

