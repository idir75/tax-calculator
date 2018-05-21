package com.idird.taxcalculator.factory;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.TaxAmountRounder;
import com.idird.taxcalculator.strategy.ImportTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.LocalTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;

public class TaxCalculationStrategyFactory {

	TaxAmountRounder defaultTaxAmountRounder = new TaxAmountRounder(DefaultConstants.DEFAULT_SCALE, DefaultConstants.DEFAULT_INCREMENT, DefaultConstants.DEFAULT_ROUNDING_MODE);
	
    private final LocalTaxCalculationStrategyImpl localTaxCalculationStrategy2 = new LocalTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, defaultTaxAmountRounder);
    private final ImportTaxCalculationStrategyImpl importTaxCalculationStrategy2 = new ImportTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE, defaultTaxAmountRounder);

    public TaxCalculationStrategy getTaxCalculationStrategy(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy2;
        }
        return localTaxCalculationStrategy2;
    }
}
