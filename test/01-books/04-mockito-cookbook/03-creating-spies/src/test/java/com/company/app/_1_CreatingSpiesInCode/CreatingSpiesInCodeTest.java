package com.company.app._1_CreatingSpiesInCode;

import com.company.app.Person;
import com.company.app.TaxFactorProcessor;
import com.company.app.TaxService;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

/*
https://www.safaribooksonline.com/library/view/mockito-cookbook/9781783982745/ch03s02.html

Create a spy by using Mockito.spy static method
 */
public class CreatingSpiesInCodeTest {

  TaxService taxService = spy(new TaxService());

  TaxFactorProcessor systemUnderTest = new TaxFactorProcessor(taxService);

  @Test
  public void should_return_default_tax_factor_for_person_from_undefined_country() {
    // given
    doNothing().when(taxService).updateTaxData(anyDouble(), any(Person.class));

    // when
    double taxFactor = systemUnderTest.processTaxFactorFor(new Person());

    // then
    then(taxFactor).isEqualTo(TaxService.DEFAULT_TAX_FACTOR);
  }
}
