package com.company.app.common;

import lombok.Data;

@Data
public class WhiskyPayload {
  private int id;
  private String name;
  private String origin;
  private State state;
}
