package com.idird.taxcalculator.strategy3;

import java.math.BigDecimal;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

public class ImportTaxCalculationStrategyImpl3 implements TaxCalculationStrategy3 {

    private final BigDecimal localTaxMt;
    private final BigDecimal importTaxMt;
    private final DecimalRounder decimalRounder;

    public ImportTaxCalculationStrategyImpl3(BigDecimal p_localTaxMt, BigDecimal p_importTaxMt, DecimalRounder p_decimalRounder) {
        this.localTaxMt = p_localTaxMt;
        this.importTaxMt = p_importTaxMt;
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
        if (p_product.isImported()) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(importTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return decimalRounder.round(taxAmount);
    }
}
