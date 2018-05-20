package com.idird.taxcalculator.strategy;

import java.math.BigDecimal;

import com.idird.taxcalculator.domain.product.Product;

public interface TaxCalculationStrategy {

    BigDecimal calculateTaxAmount(Product p_product);
}
