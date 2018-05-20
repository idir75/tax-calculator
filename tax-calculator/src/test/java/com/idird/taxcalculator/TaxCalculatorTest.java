package com.idird.taxcalculator;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Purchase;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.generator.ReceiptGenerator;
import com.idird.taxcalculator.generator.ReceiptGeneratorImpl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for Tax Calculator.
 */
public class TaxCalculatorTest extends TestCase {
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
        ReceiptGenerator receiptGenerator = new ReceiptGeneratorImpl();
        Product book = new Product("Livre", Product.Type.BOOK, 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.Type.OTHER, 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.Type.FOOD, 1, new BigDecimal("0.85"), false);
        Collection<Product> p_products = asList(book, cd, barreDeChocolat);
        ShoppingCart shoppingCart = new ShoppingCart(p_products);
        Receipt receipt = receiptGenerator.getReceipt(shoppingCart);

        assertTrue(receipt.getPurchases().size()==3);
        List<Purchase> purchases = new ArrayList<>(receipt.getPurchases());

        assertTrue(purchases.get(0).getTotalAmount().compareTo(new BigDecimal("12.49")) == 0);
        assertTrue(purchases.get(1).getTotalAmount().compareTo(new BigDecimal("16.49")) == 0);
        assertTrue(purchases.get(2).getTotalAmount().compareTo(new BigDecimal("0.85")) == 0);

        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("29.83")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("1.50")) == 0);
    }

    public void testTaxCalculatorOutput2() {
        ReceiptGenerator receiptGenerator = new ReceiptGeneratorImpl();
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;

        Product boiteChocolatImportee = new Product("Boîte de chocolats importée", Product.Type.FOOD, 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum", Product.Type.OTHER, 1, new BigDecimal("47.50"), true);
        p_products = asList(boiteChocolatImportee, flaconDeParfum);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerator.getReceipt(shoppingCart);
        System.out.println(receipt.toString());
        assertTrue(receipt.getPurchases().size() == 2);

        List<Purchase> purchases = new ArrayList<>(receipt.getPurchases());
        assertTrue(purchases.get(0).getTotalAmount().compareTo(new BigDecimal("10.50")) == 0);
        assertTrue(purchases.get(1).getTotalAmount().compareTo(new BigDecimal("54.65")) == 0);

        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("65.15")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("7.65")) == 0);
    }

    public void testTaxCalculatorOutput3() {
        ReceiptGenerator receiptGenerator = new ReceiptGeneratorImpl();
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.Type.OTHER, 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.Type.OTHER, 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.Type.MEDICAL,  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.Type.MEDICAL,1, new BigDecimal("11.25"), true);
        p_products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerator.getReceipt(shoppingCart);

        assertTrue(receipt.getPurchases().size() == 4);

        List<Purchase> purchases = new ArrayList<>(receipt.getPurchases());
        assertTrue(purchases.get(0).getTotalAmount().compareTo(new BigDecimal("32.19")) == 0);
        assertTrue(purchases.get(1).getTotalAmount().compareTo(new BigDecimal("20.89")) == 0);
        assertTrue(purchases.get(2).getTotalAmount().compareTo(new BigDecimal("9.75")) == 0);
        assertTrue(purchases.get(3).getTotalAmount().compareTo(new BigDecimal("11.85")) == 0);

        assertTrue(receipt.getTotalAmount().compareTo(new BigDecimal("74.68")) == 0);
        assertTrue(receipt.getTaxAmout().compareTo(new BigDecimal("6.70")) == 0);
    }
}
