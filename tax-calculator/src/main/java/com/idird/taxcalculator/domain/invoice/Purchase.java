package com.idird.taxcalculator.domain.invoice;

import com.idird.taxcalculator.domain.product.Product;

import java.math.BigDecimal;

public class Purchase {

    private Product product;
    private BigDecimal taxAmount;
    private BigDecimal totalAmount;
    private int quantity;

    public Purchase(Product p_product, int p_quantity, BigDecimal p_taxAmount, BigDecimal p_totalAmount) {
        this.product = p_product;
        this.quantity = p_quantity;
        this.taxAmount = p_taxAmount;
        this.totalAmount = p_totalAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(quantity);
        result.append(" ");
        result.append(product.toString());
        result.append(" : " + getTotalAmount());
        return result.toString();
    }
}
