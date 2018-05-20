package com.idird.taxcalculator.generator;

import com.idird.taxcalculator.domain.product.ShoppingBag;
import com.idird.taxcalculator.domain.receipt.Receipt;

public interface ReceiptGenerator {

    Receipt getReceipt(ShoppingBag p_shoppingCart);
}
