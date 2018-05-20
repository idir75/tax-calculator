package com.idird.taxcalculator.strategy2;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;

import java.math.BigDecimal;

public class ImportTaxCalculationStrategyImpl2 extends DefaultTaxCalculationStrategy2 {

    private final BigDecimal localTaxMt;
    private final BigDecimal importTaxMt;

    public ImportTaxCalculationStrategyImpl2(BigDecimal p_localTaxMt, BigDecimal p_importTaxMt) {
        super(new DecimalRounder());
        this.localTaxMt = p_localTaxMt;
        this.importTaxMt = p_importTaxMt;
    }

    public ImportTaxCalculationStrategyImpl2(BigDecimal p_localTaxMt, BigDecimal p_importTaxMt, DecimalRounder p_decimalRounder) {
        super(p_decimalRounder);
        this.localTaxMt = p_localTaxMt;
        this.importTaxMt = p_importTaxMt;
    }

    @Override
    public BigDecimal calculateLocalTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        if (p_product.getType().equals(Product.Type.OTHER)) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;
    }

    @Override
    public BigDecimal calculateImportTaxAmount(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        if (p_product.isImported()) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(importTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;
    }
}
