package com.idird.taxcalculator.generator;

import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;

public interface ReceiptGenerator {

    Receipt getReceipt(ShoppingCart p_shoppingCart);
}
