package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

import java.math.BigDecimal;

public abstract class DefaultTaxCalculationStrategy implements TaxCalculationStrategy {

    private final DecimalRounder decimalRounder;

    public DefaultTaxCalculationStrategy(DecimalRounder p_decimalRounder) {
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

        /*if (p_product.getType().equals(Product.Type.OTHER)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }*/
        taxAmount = taxAmount.add(calculateLocalTaxAmount(p_product));
        /*if (p_product.isImported()) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(importTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }*/
        taxAmount = taxAmount.add(calculateImportTaxAmount(p_product));
        if (decimalRounder != null) {
            return decimalRounder.roundByDefault(taxAmount);
        }
        return taxAmount;
    }

    public abstract BigDecimal calculateLocalTaxAmount(Product p_product);

    public abstract BigDecimal calculateImportTaxAmount(Product p_product);
}
