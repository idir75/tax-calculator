package com.idird.taxcalculator;

import static java.util.Arrays.asList;

import java.math.BigDecimal;
import java.util.Collection;

import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;
import com.idird.taxcalculator.generator.ReceiptGenerator;
import com.idird.taxcalculator.generator.ReceiptGeneratorImpl;

/**
 * Tax Calculator project
 *
 */
public class TaxCalculator
{
    public static void main( String[] args )  {
        ReceiptGenerator receiptGenerator = new ReceiptGeneratorImpl();
        Collection<Product> p_products;
        ShoppingCart shoppingCart;
        Receipt receipt;

        Product book = new Product("Livre", Product.Type.BOOK, 1, new BigDecimal("12.49"), false);
        Product cd = new Product("CD musical", Product.Type.OTHER, 1, new BigDecimal("14.99"), false);
        Product barreDeChocolat = new Product("barre de chocolat", Product.Type.FOOD, 1, new BigDecimal("0.85"), false);
        p_products = asList(book, cd, barreDeChocolat);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerator.getReceipt(shoppingCart);

        System.out.println("Output 1");
        System.out.println(receipt.toString());

        System.out.println("\n Output 2");
        Product boiteChocolatImportee = new Product("Boîte de chocolat importée", Product.Type.FOOD, 1, new BigDecimal("10.00"), true);
        Product flaconDeParfum = new Product("Flacon de parfum importé", Product.Type.OTHER, 1, new BigDecimal("47.50"), true);
        p_products = asList(boiteChocolatImportee, flaconDeParfum);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerator.getReceipt(shoppingCart);
        System.out.println(receipt.toString());

        System.out.println("\n Output 3");
        Product flaconDeParfum2 = new Product("Flacon de parfum importé", Product.Type.OTHER, 1, new BigDecimal("27.99"), true);
        Product flaconDeParfum3 = new Product("Flacon de parfum", Product.Type.OTHER, 1, new BigDecimal("18.99"), false);
        Product boiteDePilulesContreLaMigraine = new Product("boîte de pilules contre la migraine", Product.Type.MEDICAL,  1, new BigDecimal("9.75"), false);
        Product boiteDeChocolatImportee = new Product("boîte de chocolat importée", Product.Type.MEDICAL,  1, new BigDecimal("11.25"), true);
        p_products = asList(flaconDeParfum2, flaconDeParfum3, boiteDePilulesContreLaMigraine, boiteDeChocolatImportee);
        shoppingCart = new ShoppingCart(p_products);
        receipt = receiptGenerator.getReceipt(shoppingCart);
        System.out.println(receipt.toString());
    }
}
