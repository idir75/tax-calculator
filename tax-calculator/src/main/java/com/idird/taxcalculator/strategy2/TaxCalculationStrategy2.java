package com.idird.taxcalculator.strategy2;

import com.idird.taxcalculator.domain.product.Product;

import java.math.BigDecimal;

public interface TaxCalculationStrategy2 {

    BigDecimal calculateTaxAmount(Product p_product);
}
