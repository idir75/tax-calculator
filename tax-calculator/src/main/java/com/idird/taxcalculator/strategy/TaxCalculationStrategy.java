package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.domain.product.Product;

import java.math.BigDecimal;

public interface TaxCalculationStrategy {

    BigDecimal calculateTaxAmount(Product p_product);
}
