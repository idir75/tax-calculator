package com.idird.taxcalculator.invoice;

import com.idird.taxcalculator.domain.invoice.Purchase;
import com.idird.taxcalculator.domain.product.Product;
import com.idird.taxcalculator.domain.invoice.ShoppingCart;
import com.idird.taxcalculator.domain.invoice.Invoice;

public interface InvoiceGenerator {

    Invoice generateInvoice(ShoppingCart p_shoppingCart);
    Purchase generatePurchase(Product p_product, int p_quantity);
}
