package com.company.app;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class RodCutterTest {
    private RodCutter rodCutter;
    private List<Integer> prices;
    protected RodCutter createCutter(){
        return new RodCutter(false);
    }
    @Before
    public void initialize(){
        rodCutter = createCutter();
        prices = Arrays.asList(1,1,2,2,3,4,5);
    }

    public static <X extends Throwable> Throwable assertThrows(final Class<X> exceptionClass, final Runnable block) {
        try{
            block.run();
        }
        catch (Throwable e){
            if(exceptionClass.isInstance(e)){
                return e;
            }
        }
        fail("Failed to throw expected exception");
        return null;
    }

    @Test
    public void ConciseExceptionTest(){
        rodCutter.setPrices(prices);
        assertThrows(RodCutterException.class, () -> rodCutter.maxProfit(0));
    }

    // it takes some effort to understand
    @Test
    public void VerboseExceptionTest(){
        rodCutter.setPrices(prices);
        try{
            rodCutter.maxProfit(0);
            fail("Expected exception for zero length");
        }
        catch (RodCutterException e){
            assertTrue("expected", true);
        }
    }

    // issue: it will continue pass when the rodCutter.maxProfit() throws different exception later
    @Test(expected = RodCutterException.class)
    public void TerseExceptionTest(){
        rodCutter.setPrices(prices);
        rodCutter.maxProfit(0);
    }
}
