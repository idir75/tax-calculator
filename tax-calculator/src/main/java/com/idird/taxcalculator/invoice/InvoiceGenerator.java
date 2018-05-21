package com.idird.taxcalculator.invoice;

import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.invoice.Invoice;

public interface InvoiceGenerator {

    Invoice getInvoice(ShoppingCart p_shoppingCart);
}
