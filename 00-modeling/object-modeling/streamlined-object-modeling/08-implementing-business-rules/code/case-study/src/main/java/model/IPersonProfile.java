package model;

public interface IPersonProfile {
  // Accessors - get properties
  String getName();
  String getTitle();
  String getEmail();

  // Determine Mine: answers requests for current state information
  boolean hasValidEmail();
}
