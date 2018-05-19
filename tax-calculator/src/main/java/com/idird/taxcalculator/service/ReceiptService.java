package com.idird.taxcalculator.service;

import com.idird.taxcalculator.domain.product.ShoppingCart;
import com.idird.taxcalculator.domain.receipt.Receipt;

public interface ReceiptService {

    Receipt getReceipt(ShoppingCart p_shoppingCart);
}
