package com.idird.taxcalculator.strategy;

import com.idird.taxcalculator.domain.product.Product;

import java.lang.ref.PhantomReference;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxCalculationStrategyImpl implements TaxCalculationStrategy {

    private final BigDecimal localTaxMt;
    private final BigDecimal importTaxMt;
    private final int scale;

    public TaxCalculationStrategyImpl(BigDecimal p_localTaxMt, BigDecimal p_importTaxMt, int p_scale) {
        this.localTaxMt = p_localTaxMt;
        this.importTaxMt = p_importTaxMt;
        this.scale = p_scale;
    }

    public BigDecimal calculateTaxAmout(Product p_product) {
        BigDecimal taxAmount = BigDecimal.ZERO;
        if (p_product.getType() == null) {
            return taxAmount;
        }

        //TODO
        if (p_product.getPrice() == null || p_product.getPrice().compareTo(BigDecimal.ZERO) == 0) {
            return taxAmount;
        }

        if (p_product.getType().equals(Product.TYPE.OTHER.name())) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(localTaxMt).divide(TaxConstants.ONE_HUNDRED));
        }
        if (p_product.isImported()) {
            taxAmount = taxAmount.add(p_product.getPrice().multiply(importTaxMt).divide(TaxConstants.ONE_HUNDRED));
        }
        return roundTaxAmout(taxAmount);
    }

    private BigDecimal roundTaxAmout(BigDecimal p_taxAmount) {
        if (p_taxAmount == null) {
            return BigDecimal.ZERO;
        }
        return p_taxAmount.multiply(TaxConstants.TWENTY).setScale(0, RoundingMode.UP).divide(TaxConstants.TWENTY).setScale(scale);
    }
}
