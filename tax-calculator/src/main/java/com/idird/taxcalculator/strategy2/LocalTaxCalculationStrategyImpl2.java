package com.idird.taxcalculator.strategy2;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

import java.math.BigDecimal;

public class LocalTaxCalculationStrategyImpl2 extends DefaultTaxCalculationStrategy2 {

    private final BigDecimal localTaxMt;

    public LocalTaxCalculationStrategyImpl2(BigDecimal p_localTaxMt) {
        super(new DecimalRounder());
        this.localTaxMt = p_localTaxMt;
    }

    public LocalTaxCalculationStrategyImpl2(BigDecimal p_localTaxMt, DecimalRounder p_decimalRounder) {
        super(p_decimalRounder);
        this.localTaxMt = p_localTaxMt;
    }

    @Override
    public BigDecimal calculateLocalTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        if (p_product.getType().equals(Product.Type.OTHER)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;
    }

    @Override
    public BigDecimal calculateImportTaxAmount(Product p_product) {
        return BigDecimal.ZERO;
    }

    public BigDecimal getLocalTaxMt() {
        return localTaxMt;
    }
}
