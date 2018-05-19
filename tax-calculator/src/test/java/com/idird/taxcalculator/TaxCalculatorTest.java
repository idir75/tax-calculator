package com.idird.taxcalculator;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.service.ReceiptService;
import com.idird.taxcalculator.service.ReceiptServiceImpl;
import com.idird.taxcalculator.strategy.TaxCalculationStrategy;
import com.idird.taxcalculator.strategy.TaxCalculationStrategyImpl;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.math.BigDecimal;
import java.util.Collection;

import static java.util.Arrays.asList;

/**
 * Unit test for Tax Calculator.
 */
public class TaxCalculatorTest extends TestCase  {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TaxCalculatorTest( String testName )  {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( TaxCalculatorTest.class );
    }

    /**
     * Test
     */


    public void testTaxCalculatorOutput1() {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptService receiptService = new ReceiptServiceImpl(taxCalculationStrategy);
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;
        Product book = new Product("Livre", Product.TYPE.BOOK.name(), 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.TYPE.OTHER.name(), 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.TYPE.FOOD.name(), 1, new BigDecimal("0.85"), false);
        p_products = asList(book, cd, barreDeChocolat);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptService.getReceipt(shoppingCart);

        assertTrue(receipt.getPurchases().size()==3);
        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("29.83")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("1.50")) == 0);
    }

    public void testTaxCalculatorOutput2() {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptService receiptService = new ReceiptServiceImpl(taxCalculationStrategy);
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;

        Product boiteChocolatImportee = new Product("Boîte de chocolats importée", Product.TYPE.FOOD.name(), 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum", Product.TYPE.OTHER.name(), 1, new BigDecimal("47.50"), true);
        p_products = asList(boiteChocolatImportee, flaconDeParfum);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptService.getReceipt(shoppingCart);

        assertTrue(receipt.getPurchases().size() == 2);
        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("65.15")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("7.65")) == 0);
    }

    public void testTaxCalculatorOutput3() {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptService receiptService = new ReceiptServiceImpl(taxCalculationStrategy);
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.TYPE.OTHER.name(), 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.TYPE.OTHER.name(), 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("11.25"), true);
        p_products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptService.getReceipt(shoppingCart);

        assertTrue(receipt.getPurchases().size() == 4);
        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("74.68")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("6.70")) == 0);
    }
}
