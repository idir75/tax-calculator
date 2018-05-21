package com.idird.taxcalculator.rounding;

import com.idird.taxcalculator.constants.DefaultConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxAmountRounder {

    private int scale;
    private BigDecimal roundingRate;
    private RoundingMode roundingMode;

    public TaxAmountRounder(final int p_scale, final BigDecimal p_roundingRate, final RoundingMode p_roundingMode) {
        this.scale = p_scale;
        this.roundingRate = p_roundingRate;
        this.roundingMode = p_roundingMode;
    }

    public BigDecimal round(BigDecimal p_amount) {
        if (roundingRate.signum() == 0) {
            return p_amount;
        }

        BigDecimal percentage = roundingRate.divide(DefaultConstants.ONE_HUNDRED);
        BigDecimal ratio = p_amount.divide(percentage).setScale(0, roundingMode);
        BigDecimal result = ratio.multiply(percentage);
        return result.setScale(scale);
    }
}
