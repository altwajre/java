package com.company.app;

public class TaxFactorInformationProvider {

  private final TaxService taxService;

  public TaxFactorInformationProvider(TaxService taxService) {
    this.taxService = taxService;
  }

  public String formatIrsAddress(Person person){
    double currentTaxFactorFor = taxService.getCurrentTaxFactorFor(person);
    System.out.println(currentTaxFactorFor);
    String irsAddress = taxService.getInternalRevenueServiceAddress(person.getCountryName());
    return "IRS:[" + irsAddress + "]";
  }
}
