package com.idird.taxcalculator;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.service.ReceiptGenerationService;
import com.idird.taxcalculator.service.ReceiptGenerationServiceImpl;
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
        ReceiptGenerationService receiptGenerationService = new ReceiptGenerationServiceImpl(taxCalculationStrategy);
        Product book = new Product("Livre", Product.TYPE.BOOK.name(), 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.TYPE.OTHER.name(), 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.TYPE.FOOD.name(), 1, new BigDecimal("0.85"), false);
        Collection<Product> products = asList(book, cd, barreDeChocolat);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        Receipt receipt = receiptGenerationService.getReceipt(shoppingCart);

        assertEquals(3, receipt.getPurchases().size());
        assertEquals(0, receipt.getTotalAmount().compareTo(new BigDecimal("29.83")));
        assertEquals(0, receipt.getTaxAmout().compareTo(new BigDecimal("1.50")));
    }

    public void testTaxCalculatorOutput2() {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptGenerationService receiptGenerationService = new ReceiptGenerationServiceImpl(taxCalculationStrategy);
        Product boiteChocolatImportee = new Product("Boîte de chocolats importée", Product.TYPE.FOOD.name(), 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum", Product.TYPE.OTHER.name(), 1, new BigDecimal("47.50"), true);
        Collection<Product> product = asList(boiteChocolatImportee, flaconDeParfum);
        ShoppingCart shoppingCart = new ShoppingCart(product);
        Receipt receipt = receiptGenerationService.getReceipt(shoppingCart);
        System.out.println(receipt.toString());
        assertEquals(2, receipt.getPurchases().size());
        assertEquals(0, receipt.getTotalAmount().compareTo(new BigDecimal("65.15")));
        assertEquals(0, receipt.getTaxAmout().compareTo(new BigDecimal("7.65")));
    }

    public void testTaxCalculatorOutput3() {
        BigDecimal localTaxMt = new BigDecimal("10");
        BigDecimal importTaxMt = new BigDecimal("5");
        int scale = 2;
        TaxCalculationStrategy taxCalculationStrategy = new TaxCalculationStrategyImpl(localTaxMt, importTaxMt, scale);
        ReceiptGenerationService receiptGenerationService = new ReceiptGenerationServiceImpl(taxCalculationStrategy);
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.TYPE.OTHER.name(), 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.TYPE.OTHER.name(), 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.TYPE.MEDICAL.name(),  1, new BigDecimal("11.25"), true);
        Collection<Product> products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        ShoppingCart shoppingCart = new ShoppingCart(products);
        Receipt receipt = receiptGenerationService.getReceipt(shoppingCart);

        assertEquals(4, receipt.getPurchases().size());
        assertEquals(0, receipt.getTotalAmount().compareTo(new BigDecimal("74.68")));
        assertEquals(0, receipt.getTaxAmout().compareTo(new BigDecimal("6.70")));
    }

}
