package com.idird.taxcalculator.visitor;

import java.math.BigDecimal;
import com.idird.taxcalculator.domain.product.Product;

public class TaxVisitorImpt implements TaxVisitor {

	@Override
	public void visit(ImportedProductPurchase importedProductPurchase) {
		Product product = importedProductPurchase.getProduct();
		
		BigDecimal taxAmount = BigDecimal.ZERO;
        
        /*Product.Type productType = product.getType();
        if (!productType.equals(Product.Type.BOOK) && !productType.equals(Product.Type.FOOD) && !productType.equals(Product.Type.MEDICAL)) {
            taxAmount = taxAmount.add(product.getPrice().multiply(localTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        if (product.isImported()) {
            taxAmount = taxAmount.add(product.getPrice().multiply(importTaxMt).divide(DefaultConstants.ONE_HUNDRED));
        }
        return taxAmount;*/
	}

	@Override
	public void visit(LocalProductPurchase localProdutPurchase) {
		
	}

}
