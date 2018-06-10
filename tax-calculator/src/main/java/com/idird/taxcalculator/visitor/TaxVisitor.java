package com.idird.taxcalculator.visitor;

public interface TaxVisitor {

	public void visit(ImportedProductPurchase importedProductPurchase);
	public void visit(LocalProductPurchase localProdutPurchase);
}
