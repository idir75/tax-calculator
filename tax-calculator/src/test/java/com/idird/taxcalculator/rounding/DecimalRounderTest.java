package com.idird.taxcalculator.rounding;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalRounderTest extends TestCase {

    public DecimalRounderTest( String testName )  {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( DecimalRounderTest.class );
    }

    public void testRound() {
        DecimalRounder rounder = new DecimalRounder();
        BigDecimal defaultIncrement = new BigDecimal("5");
        assertEquals(new BigDecimal("3.05"), rounder.round(new BigDecimal("3.03"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("1.10"), rounder.round(new BigDecimal("1.053"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("1.05"), rounder.round(new BigDecimal("1.05"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("2.95"), rounder.round(new BigDecimal("2.900121"), defaultIncrement, RoundingMode.UP));

        assertEquals(new BigDecimal("1.00"), rounder.round(new BigDecimal("0.99"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("1.00"), rounder.round(new BigDecimal("1.00"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("1.05"), rounder.round(new BigDecimal("1.01"), defaultIncrement, RoundingMode.UP));
        assertEquals(new BigDecimal("1.05"), rounder.round(new BigDecimal("1.02"), defaultIncrement, RoundingMode.UP));
    }
}
