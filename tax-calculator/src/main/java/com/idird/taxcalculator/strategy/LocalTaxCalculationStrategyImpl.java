package com.idird.taxcalculator.strategy;

import java.math.BigDecimal;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

public class LocalTaxCalculationStrategyImpl extends DefaultTaxCalculationStrategy {

    private final BigDecimal localTaxMt;

    public LocalTaxCalculationStrategyImpl(BigDecimal p_localTaxMt, DecimalRounder p_decimalRounder) {
        super(p_decimalRounder);
        this.localTaxMt = p_localTaxMt;
    }

    @Override
    public BigDecimal calculateSpecificTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        Product.Type productType = p_product.getType();
        if (!productType.equals(Product.Type.BOOK) && !productType.equals(Product.Type.FOOD) && !productType.equals(Product.Type.MEDICAL)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;
    }

    public BigDecimal getLocalTaxMt() {
        return localTaxMt;
    }
}
