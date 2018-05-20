package com.idird.taxcalculator.rounding;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.idird.taxcalculator.constants.DefaultConstants;

public class DecimalRounderTest extends TestCase {

    public DecimalRounderTest( String testName )  {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( DecimalRounderTest.class );
    }

    public void testDefaultRounder() {
        DecimalRounder defaultRounder = new DecimalRounder(DefaultConstants.DEFAULT_SCALE, DefaultConstants.DEFAULT_INCREMENT, DefaultConstants.DEFAULT_ROUNDING_MODE);
        
        assertEquals(new BigDecimal("3.05"), defaultRounder.round(new BigDecimal("3.03")));
        assertEquals(new BigDecimal("1.10"), defaultRounder.round(new BigDecimal("1.053")));
        assertEquals(new BigDecimal("1.05"), defaultRounder.round(new BigDecimal("1.05")));
        assertEquals(new BigDecimal("2.95"), defaultRounder.round(new BigDecimal("2.900121")));

        assertEquals(new BigDecimal("1.00"), defaultRounder.round(new BigDecimal("0.99")));
        assertEquals(new BigDecimal("1.00"), defaultRounder.round(new BigDecimal("1.00")));
        assertEquals(new BigDecimal("1.05"), defaultRounder.round(new BigDecimal("1.01")));
        assertEquals(new BigDecimal("1.05"), defaultRounder.round(new BigDecimal("1.02")));
    }
    
    public void testRounder() {
    	DecimalRounder rounder = new DecimalRounder(DefaultConstants.DEFAULT_SCALE, DefaultConstants.DEFAULT_INCREMENT, RoundingMode.DOWN);
        
        assertEquals(new BigDecimal("3.00"), rounder.round(new BigDecimal("3.03")));
        assertEquals(new BigDecimal("1.05"), rounder.round(new BigDecimal("1.053")));
        assertEquals(new BigDecimal("1.05"), rounder.round(new BigDecimal("1.06")));
        assertEquals(new BigDecimal("2.90"), rounder.round(new BigDecimal("2.900121")));

        assertEquals(new BigDecimal("0.95"), rounder.round(new BigDecimal("0.99")));
        assertEquals(new BigDecimal("1.00"), rounder.round(new BigDecimal("1.00")));
        assertEquals(new BigDecimal("1.00"), rounder.round(new BigDecimal("1.01")));
        assertEquals(new BigDecimal("1.00"), rounder.round(new BigDecimal("1.02")));
    }
    
    public void testRounder2() {
    	int scale = 3;
    	DecimalRounder rounder = new DecimalRounder(scale, DefaultConstants.DEFAULT_INCREMENT, RoundingMode.DOWN);
        
        assertEquals(new BigDecimal("3.000"), rounder.round(new BigDecimal("3.03")));
        assertEquals(new BigDecimal("1.050"), rounder.round(new BigDecimal("1.053")));
        assertEquals(new BigDecimal("1.050"), rounder.round(new BigDecimal("1.06")));
        assertEquals(new BigDecimal("2.900"), rounder.round(new BigDecimal("2.900121")));

        assertEquals(new BigDecimal("0.950"), rounder.round(new BigDecimal("0.99")));
        assertEquals(new BigDecimal("1.000"), rounder.round(new BigDecimal("1.00")));
    }
}
