package com.company.app.secure;

import lombok.Builder.Default;
import lombok.Data;

@Data
public class AbstractModel {
  private String customerId;
  private String resourceId;
  private int version;

  @Secured
  @Default
  private long createdAt = System.nanoTime();
}
