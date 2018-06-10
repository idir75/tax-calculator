package com.idird.taxcalculator;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.Collection;

import com.idird.taxcalculator.domain.invoice.Purchase;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.invoice.ShoppingCart;
import com.idird.taxcalculator.domain.invoice.Invoice;
import com.idird.taxcalculator.factory.TaxCalculationStrategyFactory;
import com.idird.taxcalculator.invoice.InvoiceGenerator;
import com.idird.taxcalculator.invoice.InvoiceGeneratorImpl;

/**
 * Tax Calculator project
 *
 */
public class TaxCalculator {
    public static void main( String[] args )  {
    	TaxCalculationStrategyFactory taxCalculationStrategyFactory = new TaxCalculationStrategyFactory();
        InvoiceGenerator invoiceGenerator = new InvoiceGeneratorImpl(taxCalculationStrategyFactory);

        Product book = new Product("Livre", Product.Type.BOOK, new BigDecimal("12.49"), false);
        Purchase bookPurchase = invoiceGenerator.generatePurchase(book, 1);

        Product cd = new Product("CD musical", Product.Type.OTHER, new BigDecimal("14.99"), false);
        Purchase cdPurchase = invoiceGenerator.generatePurchase(cd, 1);

        Product barreDeChocolat = new Product("barre de chocolat", Product.Type.FOOD, new BigDecimal("0.85"), false);
        Purchase barreDeChocolatPurchase = invoiceGenerator.generatePurchase(barreDeChocolat, 1);

        Collection<Purchase> purchases = asList(bookPurchase, cdPurchase, barreDeChocolatPurchase);
        ShoppingCart shoppingCart = new ShoppingCart(purchases);
        Invoice invoice = invoiceGenerator.generateInvoice(shoppingCart);

        System.out.println("Output 1");
        System.out.println(invoice.toString());

        System.out.println("\n Output 2");
        Product boiteChocolatImportee = new Product("Boîte de chocolat importée", Product.Type.FOOD, new BigDecimal("10.00"), true);
        Purchase boiteChocolatImporteePurchase = invoiceGenerator.generatePurchase(boiteChocolatImportee, 1);
        Product flaconDeParfum = new Product("Flacon de parfum importé", Product.Type.OTHER, new BigDecimal("47.50"), true);
        Purchase flaconDeParfumPurchase = invoiceGenerator.generatePurchase(flaconDeParfum, 1);
        purchases = asList(boiteChocolatImporteePurchase, flaconDeParfumPurchase);
        shoppingCart = new ShoppingCart(purchases);
        invoice = invoiceGenerator.generateInvoice(shoppingCart);
        System.out.println(invoice.toString());

        System.out.println("\n Output 3");
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.Type.OTHER, new BigDecimal("27.99"), true);
        Purchase flaconDeParfum2Purchase = invoiceGenerator.generatePurchase(flaconDeParfum2, 1);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.Type.OTHER, new BigDecimal("18.99"), false);
        Purchase flaconDeParfum3Purchase = invoiceGenerator.generatePurchase(flaconDeParfum3, 1);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.Type.MEDICAL,   new BigDecimal("9.75"), false);
        Purchase boiteDePilulesContreLaMigrainePurchase = invoiceGenerator.generatePurchase(boiteDePilulesContreLaMigraine, 1);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.Type.MEDICAL, new BigDecimal("11.25"), true);
        Purchase boiteDeChocolatImporteePurchase = invoiceGenerator.generatePurchase(boiteDeChocolatImportee, 1);
        purchases = asList(flaconDeParfum2Purchase, flaconDeParfum3Purchase, boiteDePilulesContreLaMigrainePurchase, boiteDeChocolatImporteePurchase);
        shoppingCart = new ShoppingCart(purchases);
        invoice = invoiceGenerator.generateInvoice(shoppingCart);
        System.out.println(invoice.toString());
    }
}
