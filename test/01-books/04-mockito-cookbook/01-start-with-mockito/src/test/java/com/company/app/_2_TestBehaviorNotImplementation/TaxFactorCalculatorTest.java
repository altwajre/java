package com.company.app._2_TestBehaviorNotImplementation;

import com.company.app.TaxFactorCalculator;
import org.junit.Test;

import static org.assertj.core.api.BDDAssertions.then;

// Mockito best practices – test behavior not implementation
public class TaxFactorCalculatorTest {

  @Test
  public void should_calculate_sum_of_factors(){
    // given
    TaxFactorCalculator systemUnderTest = new TaxFactorCalculator();
    double taxFactorOne = 1;
    double taxFactorTwo = 2;
    double expectedSum = 3;

    // when
    double sumOfFactor = systemUnderTest.calculateSum(taxFactorOne, taxFactorTwo);

    // then
    then(sumOfFactor).isEqualTo(expectedSum);
  }
}