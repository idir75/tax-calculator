package com.idird.taxcalculator.generator;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Purchase;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.factory.TaxCalculationStrategyFactory;
import com.idird.taxcalculator.strategy.LocalTaxCalculationStrategyImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class ReceiptGeneratorImpl implements ReceiptGenerator {

    public ReceiptGeneratorImpl() {
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
        TaxCalculationStrategyFactory taxCalculationStrategyFactory = new TaxCalculationStrategyFactory();
        TaxCalculationStrategy taxCalculationStrategy  = taxCalculationStrategyFactory.getTaxCalculationStrategy(p_product);
        BigDecimal taxAmount = taxCalculationStrategy.calculateTaxAmount(p_product).multiply(quantityAsBigDecimal);
        BigDecimal totalAmount = p_product.getPrice().multiply(quantityAsBigDecimal).add(taxAmount);
        return new Purchase(p_product, taxAmount, totalAmount);
    }
}
