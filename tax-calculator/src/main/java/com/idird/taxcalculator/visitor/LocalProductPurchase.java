package com.idird.taxcalculator.visitor;

public class LocalProductPurchase implements IProductPurchase {

	@Override
	public void accept(TaxVisitor taxVisitor) {
		taxVisitor.visit(this);
	}

}
