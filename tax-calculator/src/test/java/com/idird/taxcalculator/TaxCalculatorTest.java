package com.idird.taxcalculator;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Tax Calculator.
 */
public class TaxCalculatorTest extends TestCase  {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TaxCalculatorTest( String testName )  {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( TaxCalculatorTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue( true );
    }
}
