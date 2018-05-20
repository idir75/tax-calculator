package com.idird.taxcalculator.invoice;

import com.idird.taxcalculator.domain.product.ShoppingBag;
import com.idird.taxcalculator.domain.invoice.Invoice;

public interface InvoiceGenerator {

    Invoice getInvoice(ShoppingBag p_shoppingBag);
}
