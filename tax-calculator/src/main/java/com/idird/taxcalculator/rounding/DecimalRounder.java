package com.idird.taxcalculator.rounding;

import com.idird.taxcalculator.constants.DefaultConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalRounder {

    private int scale;
    private BigDecimal roundingRate;
    private RoundingMode roundingMode;

    public DecimalRounder(final int p_scale, final BigDecimal p_roundingRate, final RoundingMode p_roundingMode) {
        this.scale = p_scale;
        this.roundingRate = p_roundingRate;
        this.roundingMode = p_roundingMode;
    }

    public BigDecimal round(BigDecimal value) {
        if (roundingRate.signum() == 0) {
            return value;
        }

        BigDecimal percentage = roundingRate.divide(DefaultConstants.ONE_HUNDRED);
        BigDecimal divided = value.divide(percentage).setScale(0, roundingMode);
        BigDecimal result = divided.multiply(percentage);
        return result.setScale(scale);
    }
}
