package com.company.app._3_CreatingMocksWithDifferentDefaultAnswer;

import com.company.app.MeanTaxFactorCalculator;
import com.company.app.Person;
import com.company.app.TaxService;
import org.junit.Test;
import org.mockito.internal.stubbing.answers.ThrowsExceptionClass;

import static junit.framework.TestCase.fail;
import static org.mockito.Mockito.mock;

/*
https://www.safaribooksonline.com/library/view/mockito-cookbook/9781783982745/ch02s04.html

Creating Mocks with a different default answer

1, Use overloaded Mockito.mock(Class<T> classToMock, Answer defaultAnswer) static method

 */
public class CreatingMocksWithThrowExceptionAnswerTest {

  TaxService taxService = mock(TaxService.class, new ThrowsExceptionClass(IllegalStateException.class));

  MeanTaxFactorCalculator systemUnderTest = new MeanTaxFactorCalculator(taxService);

  @Test
  public void should_throw_exception_when_calculating_mean_tax_factor(){
    try{
      systemUnderTest.calculateMeanTaxFactorFor(new Person());
      fail("should throw exception");
    }
    catch (IllegalStateException exception){
    }
  }
}
