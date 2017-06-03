package com.company.app._4_CreatingMocksWithDifferentDefaultAnswersWithAnnotations;

import com.company.app.Person;
import com.company.app.TaxFactorInformationProvider;
import com.company.app.TaxService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.BDDAssertions.then;

/*
https://www.safaribooksonline.com/library/view/mockito-cookbook/9781783982745/ch02s05.html

Creating mocks with a different default answers with annotations

Remove the unnecessary code and make the test more readable.
1, Annotate test with @RunWith(MockitoJUnitRunner.class)
2, Pass a nondefault answer to @Mock annotated field

Answers.RETURNS_SMART_NULLS returns an empty string by default for mock object

For example, irsAddress is an empty string on code below
String irsAddress = taxService.getInternalRevenueServiceAddress(person.getCountryName());
 */
@RunWith(MockitoJUnitRunner.class)
public class CreatingMocksWithReturnsSmartNullsAnswerTest {

  @Mock(answer = Answers.RETURNS_SMART_NULLS)
  TaxService taxService;

  @InjectMocks
  TaxFactorInformationProvider systemUnderTest;

  @Test
  public void should_calculate_mean_tax_factor() {
    // when
    String parsedIrsAddress = systemUnderTest.formatIrsAddress(new Person());

    // then
    then(parsedIrsAddress).isEqualTo("IRS:[]");
  }
}
