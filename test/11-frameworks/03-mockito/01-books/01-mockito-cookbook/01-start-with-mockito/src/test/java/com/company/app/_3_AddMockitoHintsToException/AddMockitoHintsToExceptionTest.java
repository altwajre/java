package com.company.app._3_AddMockitoHintsToException;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.VerboseMockitoJUnitRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

@RunWith(VerboseMockitoJUnitRunner.class)
public class AddMockitoHintsToExceptionTest {

  static final double UNUSED_VALUE = 10;

  @Test
  public void should_calculate_mean_tax_factor() {
    // given
    TaxService taxService = given(Mockito.mock(TaxService.class).performAdditionalCalculation()).willReturn(UNUSED_VALUE).getMock();
    double currentTaxFactorFor = taxService.getCurrentTaxFactorFor(new Person());
    MeanTaxFactorCalculator systemUnderTest = new MeanTaxFactorCalculator(taxService);

    // when
    double meanTaxFactor = systemUnderTest.calculateMeanTaxFactorFor(new Person());

    // then
    then(meanTaxFactor).isEqualTo(UNUSED_VALUE);
  }

}
