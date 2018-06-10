package com.idird.taxcalculator.visitor;

import java.math.BigDecimal;

import com.idird.taxcalculator.domain.product.Product;

public class ImportedProductPurchase implements IProductPurchase {

	private Product product;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    
	@Override
	public void accept(TaxVisitor taxVisitor) {
		taxVisitor.visit(this);
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
