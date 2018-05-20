package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;
import java.math.BigDecimal;

public class LocalTaxCalculationStrategyImpl implements TaxCalculationStrategy {

    private final BigDecimal localTaxMt;
    private final DecimalRounder decimalRounder;

    public LocalTaxCalculationStrategyImpl(BigDecimal p_localTaxMt) {
        this.localTaxMt = p_localTaxMt;
        this.decimalRounder = new DecimalRounder();
    }

    public LocalTaxCalculationStrategyImpl(BigDecimal p_localTaxMt, DecimalRounder p_decimalRounder) {
        this.localTaxMt = p_localTaxMt;
        this.decimalRounder = p_decimalRounder;
    }

    @Override
    public BigDecimal calculateTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        if (p_product.getType() == null) {
            return taxAmount;
        }

        if (p_product.getPrice() == null || p_product.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            return taxAmount;
        }

        if (p_product.getType().equals(Product.Type.OTHER)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        if (decimalRounder != null) {
            return decimalRounder.roundByDefault(taxAmount);
        }
        return taxAmount;
    }

    public DecimalRounder getDecimalRounder() {
        return decimalRounder;
    }

    public BigDecimal getLocalTaxMt() {
        return localTaxMt;
    }
}
