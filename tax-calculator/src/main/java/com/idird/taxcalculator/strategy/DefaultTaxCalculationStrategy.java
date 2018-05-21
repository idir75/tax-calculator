package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.TaxAmountRounder;

import java.math.BigDecimal;

public abstract class DefaultTaxCalculationStrategy implements TaxCalculationStrategy {

    private final TaxAmountRounder taxAmountRounder;

    public DefaultTaxCalculationStrategy(TaxAmountRounder p_taxAmountRounder) {
        this.taxAmountRounder = p_taxAmountRounder;
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

        taxAmount = calculateSpecificTaxAmount(p_product);
        if (taxAmountRounder == null) {
        	return taxAmount;
        }
        return taxAmountRounder.round(taxAmount);
    }

    public abstract BigDecimal calculateSpecificTaxAmount(Product p_product);

}
