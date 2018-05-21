package com.idird.taxcalculator.strategy;

import java.math.BigDecimal;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.TaxAmountRounder;

public class LocalTaxCalculationStrategyImpl extends DefaultTaxCalculationStrategy {

    private final BigDecimal localTaxMt;

    public LocalTaxCalculationStrategyImpl(BigDecimal p_localTaxMt, TaxAmountRounder p_taxAmountRounder) {
        super(p_taxAmountRounder);
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
