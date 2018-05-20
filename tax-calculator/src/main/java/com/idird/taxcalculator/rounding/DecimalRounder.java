package com.idird.taxcalculator.rounding;

import com.idird.taxcalculator.constants.DefaultConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalRounder {

    private int scale;
    private BigDecimal round;
    private RoundingMode roundingMode;

    public DecimalRounder() {
    	this.scale = DefaultConstants.DEFAULT_SCALE;
    }

    public DecimalRounder(final int p_scale, final BigDecimal p_round, final RoundingMode p_roundingMode) {
        this.scale = p_scale;
        this.round = p_round;
        this.roundingMode = p_roundingMode;
    }

    public BigDecimal roundByDefault(BigDecimal p_amount) {
        return round(p_amount, DefaultConstants.DEFAULT_INCREMENT, DefaultConstants.DEFAULT_ROUNDING_MODE);
    }

    public BigDecimal round(BigDecimal value, BigDecimal increment, RoundingMode roundingMode) {
        if (increment.signum() == 0) {
            return value;
        }

        BigDecimal percentage = increment.divide(DefaultConstants.ONE_HUNDRED);
        BigDecimal divided = value.divide(percentage).setScale(0, roundingMode);
        BigDecimal result = divided.multiply(percentage);

        return result;

    }
}
