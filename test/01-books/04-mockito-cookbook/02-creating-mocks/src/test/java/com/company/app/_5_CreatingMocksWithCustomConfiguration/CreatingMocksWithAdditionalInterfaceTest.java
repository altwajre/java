package com.company.app._5_CreatingMocksWithCustomConfiguration;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Test;

import java.io.Serializable;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.withSettings;

/*
https://www.safaribooksonline.com/library/view/mockito-cookbook/9781783982745/ch02s06.html

Creating mocks with custom configuration

Sometimes the mock need to satisfy some additional prerequisites

1, Use MockitoSettings as code below
TaxService taxService = mock(TaxService.class, withSettings().serializable());
 */
public class CreatingMocksWithAdditionalInterfaceTest {
  static final double TAX_FACTOR = 10;
  TaxService taxService = mock(TaxService.class, withSettings().serializable());

  MeanTaxFactorCalculator systemUnderTest = new MeanTaxFactorCalculator(taxService);

  @Test
  public void should_calculate_mean_tax_factor() {
    // given
    given(taxService.getCurrentTaxFactorFor(any(Person.class))).willReturn(TAX_FACTOR);

    // when
    double meanTaxFactor = systemUnderTest.calculateMeanTaxFactorFor(new Person());

    // then
    then(meanTaxFactor).isEqualTo(TAX_FACTOR);
    then(taxService).isInstanceOf(Serializable.class);
  }
}