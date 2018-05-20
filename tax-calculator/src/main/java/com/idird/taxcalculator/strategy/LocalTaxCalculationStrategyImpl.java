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

        Product.Type productType = p_product.getType();
        if (!productType.equals(Product.Type.BOOK) && !productType.equals(Product.Type.FOOD) && !productType.equals(Product.Type.MEDICAL)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return decimalRounder.roundByDefault(taxAmount);
    }

    public DecimalRounder getDecimalRounder() {
        return decimalRounder;
    }

    public BigDecimal getLocalTaxMt() {
        return localTaxMt;
    }
}
