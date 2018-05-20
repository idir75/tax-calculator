package com.idird.taxcalculator.strategy3;

import java.math.BigDecimal;

import com.idird.taxcalculator.domain.product.Product;

public interface TaxCalculationStrategy3 {

    BigDecimal calculateTaxAmount(Product p_product);
}
