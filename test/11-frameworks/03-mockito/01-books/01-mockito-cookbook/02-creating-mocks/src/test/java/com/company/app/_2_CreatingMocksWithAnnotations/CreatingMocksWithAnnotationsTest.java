package com.company.app._2_CreatingMocksWithAnnotations;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

/*
https://www.safaribooksonline.com/library/view/mockito-cookbook/9781783982745/ch02s03.html

Creating Mocks By Annotations

Remove the unnecessary code and make the test more readable.
1, Annotate test with @RunWith(MockitoJUnitRunner.class)
2, Annotate dependencies with @Mock annotation
 */
@RunWith(MockitoJUnitRunner.class)
public class CreatingMocksWithAnnotationsTest {

  static final double TAX_FACTOR = 10;

  @Mock
  TaxService taxService;

  @InjectMocks
  MeanTaxFactorCalculator systemUnderTest;

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