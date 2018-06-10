package com.idird.taxcalculator.visitor;

import java.util.Collection;
import java.util.Collections;
import com.idird.taxcalculator.domain.invoice.Invoice;
import com.idird.taxcalculator.domain.invoice.Purchase;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.invoice.ShoppingCart;
import com.idird.taxcalculator.invoice.InvoiceGenerator;

public class InvoiceGeneratorWithVisitorImpl implements InvoiceGenerator {

	Collection<IProductPurchase> purchases;
	TaxVisitor taxVisitor;
	public InvoiceGeneratorWithVisitorImpl(Collection<IProductPurchase> p_purchases, TaxVisitor taxVisitor) {
		this.purchases = p_purchases;
		this.taxVisitor = taxVisitor;
	}

	@Override
	public Invoice generateInvoice(ShoppingCart p_shoppingCart) {
		purchases.stream().forEach(purchase -> purchase.accept(taxVisitor));
		
		Collection<Purchase> purchases = Collections.emptyList();

		return new Invoice(purchases, null, null);
	}

	@Override
	public Purchase generatePurchase(Product p_product, int p_quantity) {
		return null;
	}

	private LocalProductPurchase getLocalProductPurchase(Product p) {
		LocalProductPurchase l_LocalProductPurchase = new LocalProductPurchase();
		return l_LocalProductPurchase;
	}

	private ImportedProductPurchase getImportProductPurchase(Product p) {
		return new ImportedProductPurchase();
	}
}
