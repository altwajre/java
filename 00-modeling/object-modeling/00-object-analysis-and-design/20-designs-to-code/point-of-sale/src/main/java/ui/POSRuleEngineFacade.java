package ui;

import domain.model.Sale;
import domain.model.SalesLineItem;

/*
With this design, the complexity and implementation of how rules will be represented and evaluated are hidden
in the "rule engine" subsystem, accessed via the POSRuleEngineFacade façade. Observe that the subsystem hidden
by the façade object could contain dozens or hundreds of classes of objects, or even a non-object-oriented solution,
yet as a client to the subsystem, we see only its one public access point.
 */
public enum POSRuleEngineFacade {
  INSTANCE;

  // could contain dozens or hundreds of classes of objects
  public boolean isInvalid(SalesLineItem sli, Sale sale) {
    return false;
  }
}
