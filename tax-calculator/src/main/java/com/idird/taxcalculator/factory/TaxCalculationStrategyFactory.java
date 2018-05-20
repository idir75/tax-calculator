package com.idird.taxcalculator.factory;

import com.idird.taxcalculator.constants.DefaultConstants;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.rounding.DecimalRounder;
import com.idird.taxcalculator.strategy.ImportTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.LocalTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;
import com.idird.taxcalculator.strategy3.ImportTaxCalculationStrategyImpl3;
import com.idird.taxcalculator.strategy3.LocalTaxCalculationStrategyImpl3;
import com.idird.taxcalculator.strategy3.TaxCalculationStrategy3;

public class TaxCalculationStrategyFactory {

	DecimalRounder defaultDecimalRounder = new DecimalRounder(DefaultConstants.DEFAULT_SCALE, DefaultConstants.DEFAULT_INCREMENT, DefaultConstants.DEFAULT_ROUNDING_MODE);
	
    private final LocalTaxCalculationStrategyImpl3 localTaxCalculationStrategy = new LocalTaxCalculationStrategyImpl3(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, defaultDecimalRounder);
    private final ImportTaxCalculationStrategyImpl3 importTaxCalculationStrategy = new ImportTaxCalculationStrategyImpl3(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE, defaultDecimalRounder);

    private final LocalTaxCalculationStrategyImpl localTaxCalculationStrategy2 = new LocalTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, defaultDecimalRounder);
    private final ImportTaxCalculationStrategyImpl importTaxCalculationStrategy2 = new ImportTaxCalculationStrategyImpl(DefaultConstants.DEFAULT_LOCAL_TAX_RATE, DefaultConstants.DEFAULT_IMPORT_TAX_RATE, defaultDecimalRounder);

    
    public TaxCalculationStrategy3 getTaxCalculationStrategy3(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy;
        }
        return localTaxCalculationStrategy;
    }

    public TaxCalculationStrategy getTaxCalculationStrategy(Product product) {
        if (product.isImported()) {
            return importTaxCalculationStrategy2;
        }
        return localTaxCalculationStrategy2;
    }
}
