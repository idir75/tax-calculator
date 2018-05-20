package com.idird.taxcalculator.domain.product;

import java.util.Collection;

public class ShoppingBag {

    private Collection<Product> products;

    public ShoppingBag(Collection<Product> p_products) {
        this.products = p_products;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

}
