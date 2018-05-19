package com.idird.taxcalculator.constants;

import java.math.BigDecimal;

public final class TaxConstants {

    public static final BigDecimal TWENTY = new BigDecimal(20);
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private TaxConstants(){
        throw new AssertionError();
    }
}
