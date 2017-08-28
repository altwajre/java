package com.company.app._1_MockitoForJUnit;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

// Getting started with Mockito for JUnit
public class MockitoForJunit {
  static final double TAX_FACTOR = 10;
  @Mock
  TaxService taxService;
  @InjectMocks
  MeanTaxFactorCalculator systemUnderTest;
  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }
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
