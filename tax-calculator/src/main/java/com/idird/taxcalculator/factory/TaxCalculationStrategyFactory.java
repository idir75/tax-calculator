package com.idird.taxcalculator.factory;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;
import com.idird.taxcalculator.strategy.ImportTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.LocalTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;
import com.idird.taxcalculator.strategy2.ImportTaxCalculationStrategyImpl2;
import com.idird.taxcalculator.strategy2.LocalTaxCalculationStrategyImpl2;
import com.idird.taxcalculator.strategy2.TaxCalculationStrategy2;

public class TaxCalculationStrategyFactory {

	DecimalRounder defaultDecimalRounder = new DecimalRounder(DefaultConstants.DEFAULT_SCALE, DefaultConstants.DEFAULT_INCREMENT, DefaultConstants.DEFAULT_ROUNDING_MODE);
	
    private final LocalTaxCalculationStrategyImpl localTaxCalculationStrategy = new LocalTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, defaultDecimalRounder);
    private final ImportTaxCalculationStrategyImpl importTaxCalculationStrategy = new ImportTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE, defaultDecimalRounder);

    private final LocalTaxCalculationStrategyImpl2 localTaxCalculationStrategy2 = new LocalTaxCalculationStrategyImpl2(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, defaultDecimalRounder);
    private final ImportTaxCalculationStrategyImpl2 importTaxCalculationStrategy2 = new ImportTaxCalculationStrategyImpl2(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE, defaultDecimalRounder);

    
    public TaxCalculationStrategy getTaxCalculationStrategy(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy;
        }
        return localTaxCalculationStrategy;
    }

    public TaxCalculationStrategy2 getTaxCalculationStrategy2(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy2;
        }
        return localTaxCalculationStrategy2;
    }
}
