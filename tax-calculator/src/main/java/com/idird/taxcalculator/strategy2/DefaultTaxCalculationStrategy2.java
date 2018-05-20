package com.idird.taxcalculator.strategy2;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

import java.math.BigDecimal;

public abstract class DefaultTaxCalculationStrategy2 implements TaxCalculationStrategy2 {

    private final DecimalRounder decimalRounder;

    //TODO ajouter un Constructeur par defaut pour le rounder
    public DefaultTaxCalculationStrategy2(DecimalRounder p_decimalRounder) {
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

        taxAmount = calculateSpecificTaxAmount(p_product);
        if (decimalRounder != null) {
            return decimalRounder.roundByDefault(taxAmount);
        }
        return taxAmount;
    }

    public abstract BigDecimal calculateSpecificTaxAmount(Product p_product);

}
