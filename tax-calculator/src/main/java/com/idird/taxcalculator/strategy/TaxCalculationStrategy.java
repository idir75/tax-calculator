package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.domain.product.Product;

import java.math.BigDecimal;

public interface TaxCalculationStrategy {
    BigDecimal calculateTaxAmout(Product p_product);
}
