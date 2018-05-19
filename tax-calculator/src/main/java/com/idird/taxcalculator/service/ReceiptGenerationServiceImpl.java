package com.idird.taxcalculator.service;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Purchase;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ReceiptGenerationServiceImpl implements ReceiptGenerationService {

    private final TaxCalculationStrategy taxCalculationStrategy;

    public ReceiptGenerationServiceImpl(TaxCalculationStrategy p_taxCalculationStrategy) {
        this.taxCalculationStrategy = p_taxCalculationStrategy;
    }

    @Override
    public Receipt getReceipt(ShoppingCart p_shoppingCart) {
        Collection<Purchase> purchases = p_shoppingCart.getProducts().stream().map(this::getPurchase).collect(Collectors.toCollection(ArrayList::new));
        BigDecimal totalTaxAmount = purchases.stream().map(purchase -> purchase.getTaxAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalAmount = purchases.stream().map(purchase -> purchase.getTotalAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Receipt(purchases, totalTaxAmount, totalAmount);
    }

    private Purchase getPurchase(Product p_product) {
        //TODO mauvais
        BigDecimal quantityAsBigDecimal = new BigDecimal(p_product.getQuantity());
        BigDecimal taxAmount = taxCalculationStrategy.calculateTaxAmout(p_product).multiply(quantityAsBigDecimal);
        BigDecimal totalAmount = p_product.getPrice().multiply(quantityAsBigDecimal).add(taxAmount);
        return new Purchase(p_product, taxAmount, totalAmount);
    }
}
