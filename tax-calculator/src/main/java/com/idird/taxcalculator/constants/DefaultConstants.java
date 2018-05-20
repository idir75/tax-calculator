package com.idird.taxcalculator.constants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class DefaultConstants {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final BigDecimal DEFAULT_INCREMENT = new BigDecimal(5);
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.UP;

    public static final BigDecimal DEFAULT_LOCAL_TAX_RATE = new BigDecimal("10");
    public static final BigDecimal DEFAULT_IMPORT_TAX_RATE = new BigDecimal("5");

    public static final int DEFAULT_SCALE = 2;
    
    private DefaultConstants(){
        throw new AssertionError();
    }
}
