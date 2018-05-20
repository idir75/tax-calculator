package com.idird.taxcalculator.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingBag;
import com.idird.taxcalculator.domain.invoice.Purchase;
import com.idird.taxcalculator.domain.invoice.Invoice;
import com.idird.taxcalculator.factory.TaxCalculationStrategyFactory;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;

public class InvoiceGeneratorImpl implements InvoiceGenerator {

	TaxCalculationStrategyFactory taxCalculationStrategyFactory;
	
    public InvoiceGeneratorImpl(TaxCalculationStrategyFactory p_taxCalculationStrategyFactory) {
    	taxCalculationStrategyFactory = p_taxCalculationStrategyFactory;
    }

    @Override
    public Invoice getInvoice(ShoppingBag p_shoppingBag) {
        Collection<Purchase> purchases = p_shoppingBag.getProducts().stream().map(this::getPurchase).collect(Collectors.toCollection(ArrayList::new));
        BigDecimal totalTaxAmount = purchases.stream().map(purchase -> purchase.getTaxAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalAmount = purchases.stream().map(purchase -> purchase.getTotalAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Invoice(purchases, totalTaxAmount, totalAmount);
    }

    private Purchase getPurchase(Product p_product) {
        BigDecimal quantityAsBigDecimal = new BigDecimal(p_product.getQuantity());
        
        //TaxCalculationStrategy taxCalculationStrategy  = taxCalculationStrategyFactory.getTaxCalculationStrategy(p_product);

        TaxCalculationStrategy taxCalculationStrategy  = taxCalculationStrategyFactory.getTaxCalculationStrategy(p_product);
        BigDecimal taxAmount = taxCalculationStrategy.calculateTaxAmount(p_product).multiply(quantityAsBigDecimal);
        BigDecimal totalAmount = p_product.getPrice().multiply(quantityAsBigDecimal).add(taxAmount);
        return new Purchase(p_product, taxAmount, totalAmount);
    }
}
