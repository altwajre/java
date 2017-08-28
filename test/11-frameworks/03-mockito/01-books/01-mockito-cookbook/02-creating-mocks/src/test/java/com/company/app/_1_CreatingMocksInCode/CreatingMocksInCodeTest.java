package com.company.app._1_CreatingMocksInCode;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

// create a mock by using the Mockito.mock static method
public class CreatingMocksInCodeTest {

  static final double TAX_FACTOR = 10;

  TaxService taxService = mock(TaxService.class);

  MeanTaxFactorCalculator systemUnderTest = new MeanTaxFactorCalculator(taxService);

  @Test
  public void should_calculate_mean_tax_factor() {
    // given
    given(taxService.getCurrentTaxFactorFor(any(Person.class))).willReturn(TAX_FACTOR);

    // when
    double meanTaxFactor = systemUnderTest.calculateMeanTaxFactorFor(new Person());

    // then
    then(meanTaxFactor).isEqualTo(TAX_FACTOR);
  }
}
