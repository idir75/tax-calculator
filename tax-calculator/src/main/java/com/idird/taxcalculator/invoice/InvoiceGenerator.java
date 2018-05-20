package com.idird.taxcalculator.invoice;

import com.idird.taxcalculator.domain.product.ShoppingBag;
import com.idird.taxcalculator.domain.receipt.Receipt;

public interface InvoiceGenerator {

    Receipt getReceipt(ShoppingBag p_shoppingCart);
}
