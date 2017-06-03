package com.company.app;

public interface TaxService {
  double getCurrentTaxFactorFor(Person person);
  double performAdditionalCalculation();
}
