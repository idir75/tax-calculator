package com.idird.taxcalculator.strategy;

import java.math.BigDecimal;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

public class ImportTaxCalculationStrategyImpl extends DefaultTaxCalculationStrategy {

    private final BigDecimal localTaxMt;
    private final BigDecimal importTaxMt;

    public ImportTaxCalculationStrategyImpl(BigDecimal p_localTaxMt, BigDecimal p_importTaxMt, DecimalRounder p_decimalRounder) {
        super(p_decimalRounder);
        this.localTaxMt = p_localTaxMt;
        this.importTaxMt = p_importTaxMt;
    }

    @Override
    public BigDecimal calculateSpecificTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        
        Product.Type productType = p_product.getType();
        if (!productType.equals(Product.Type.BOOK) && !productType.equals(Product.Type.FOOD) && !productType.equals(Product.Type.MEDICAL)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        if (p_product.isImported()) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(importTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;
    }

}
