package com.idird.taxcalculator.rounding;

import com.idird.taxcalculator.constants.DefaultConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxAmountRounder {

    private int scale;
    private BigDecimal roundingMt;
    private RoundingMode roundingMode;

    public TaxAmountRounder(final int p_scale, final BigDecimal p_roundingMt, final RoundingMode p_roundingMode) {
        this.scale = p_scale;
        this.roundingMt = p_roundingMt;
        this.roundingMode = p_roundingMode;
    }

    public BigDecimal round(BigDecimal p_amount) {
        if (roundingMt.signum() == 0) {
            return p_amount;
        }

        BigDecimal percentage = roundingMt.divide(DefaultConstants.ONE_HUNDRED);
        BigDecimal ratio = p_amount.divide(percentage).setScale(0, roundingMode);
        BigDecimal result = ratio.multiply(percentage);
        return result.setScale(scale);
    }
}
