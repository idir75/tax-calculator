package com.idird.taxcalculator;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.service.ReceiptGenerationService;
import com.idird.taxcalculator.service.ReceiptGenerationServiceImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;
import com.idird.taxcalculator.strategy.TaxCalculationStrategyImpl;
import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Tax Calculator project
 *
 */
public class TaxCalculator
{
    public static void main( String[] args )  {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptGenerationService receiptGenerationService = new ReceiptGenerationServiceImpl(taxCalculationStrategy);
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;

        Product book = new Product("Livre", Product.TYPE.BOOK.name(), 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.TYPE.OTHER.name(), 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.TYPE.FOOD.name(), 1, new BigDecimal("0.85"), false);
        p_products = asList(book, cd, barreDeChocolat);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerationService.getReceipt(shoppingCart);

        System.out.println("Output 1");
        System.out.println(receipt.toString());

        System.out.println("\n Output 2");
        Product boiteChocolatImportee = new Product("Boîte de chocolats importée", Product.TYPE.FOOD.name(), 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum importé", Product.TYPE.OTHER.name(), 1, new BigDecimal("47.50"), true);
        p_products = asList(boiteChocolatImportee, flaconDeParfum);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerationService.getReceipt(shoppingCart);
        System.out.println(receipt.toString());

        System.out.println("\n Output 3");
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.TYPE.OTHER.name(), 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.TYPE.OTHER.name(), 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("11.25"), true);
        p_products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerationService.getReceipt(shoppingCart);
        System.out.println(receipt.toString());
    }
}
