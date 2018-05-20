package com.idird.taxcalculator;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.Collection;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingBag;
import com.idird.taxcalculator.domain.invoice.Invoice;
import com.idird.taxcalculator.factory.TaxCalculationStrategyFactory;
import com.idird.taxcalculator.invoice.InvoiceGenerator;
import com.idird.taxcalculator.invoice.InvoiceGeneratorImpl;

/**
 * Tax Calculator project
 *
 */
public class TaxCalculator
{
    public static void main( String[] args )  {
    	TaxCalculationStrategyFactory taxCalculationStrategyFactory = new TaxCalculationStrategyFactory();
        InvoiceGenerator invoiceGenerator = new InvoiceGeneratorImpl(taxCalculationStrategyFactory);

        Product book = new Product("Livre", Product.Type.BOOK, 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.Type.OTHER, 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.Type.FOOD, 1, new BigDecimal("0.85"), false);
        Collection<Product> p_products = asList(book, cd, barreDeChocolat);
        ShoppingBag shoppingBag = new ShoppingBag(p_products);
        Invoice invoice = invoiceGenerator.getInvoice(shoppingBag);

        System.out.println("Output 1");
        System.out.println(invoice.toString());

        System.out.println("\n Output 2");
        Product boiteChocolatImportee = new Product("Boîte de chocolat importée", Product.Type.FOOD, 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum importé", Product.Type.OTHER, 1, new BigDecimal("47.50"), true);
        p_products = asList(boiteChocolatImportee, flaconDeParfum);
        shoppingBag = new ShoppingBag(p_products);
        invoice = invoiceGenerator.getInvoice(shoppingBag);
        System.out.println(invoice.toString());

        System.out.println("\n Output 3");
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.Type.OTHER, 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.Type.OTHER, 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.Type.MEDICAL,  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.Type.MEDICAL,  1, new BigDecimal("11.25"), true);
        p_products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        shoppingBag = new ShoppingBag(p_products);
        invoice = invoiceGenerator.getInvoice(shoppingBag);
        System.out.println(invoice.toString());
    }
}
