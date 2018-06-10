package com.idird.taxcalculator.visitor;

public interface IProductPurchase {
	public void accept(TaxVisitor taxVisitor);
}
