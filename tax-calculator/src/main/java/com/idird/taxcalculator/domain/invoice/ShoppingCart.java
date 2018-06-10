package com.idird.taxcalculator.domain.invoice;

import com.idird.taxcalculator.domain.invoice.Purchase;

import java.util.Collection;

public class ShoppingCart {

    private Collection<Purchase> purchases;

    public ShoppingCart(Collection<Purchase> p_purchases) {
        this.purchases = p_purchases;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }
}
