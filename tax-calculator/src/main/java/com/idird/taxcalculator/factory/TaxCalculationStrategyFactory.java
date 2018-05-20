package com.idird.taxcalculator.factory;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.strategy.ImportTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.LocalTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;

public class TaxCalculationStrategyFactory {

    private final LocalTaxCalculationStrategyImpl localTaxCalculationStrategy = new LocalTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE);
    private final ImportTaxCalculationStrategyImpl importTaxCalculationStrategy = new ImportTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE);

    public TaxCalculationStrategy getTaxCalculationStrategy(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy;
        }
        return localTaxCalculationStrategy;
    }
}
