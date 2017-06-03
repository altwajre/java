package com.company.app;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.*;

//@RunWith(PowerMock)
public class TaxFactorCalculatorTest {

  static final double TAX_FACTOR = 10000;

  @Mock TaxService taxService;

  @InjectMocks
  TaxFactorCalculator systemUnderTest;

}