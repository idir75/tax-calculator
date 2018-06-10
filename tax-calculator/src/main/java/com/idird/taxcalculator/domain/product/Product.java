package com.idird.taxcalculator.domain.product;

import java.math.BigDecimal;

public class Product {

    public enum Type {
        BOOK, FOOD, MEDICAL, OTHER;
    }

    private String name;

    private Type type;

    private BigDecimal price;

    private boolean imported;

    public Product(String p_name, Type p_type, BigDecimal p_price, boolean p_imported) {
        this.name = p_name;
        this.type = p_type;
        this.price = p_price;
        this.imported = p_imported;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isImported() {
        return imported;
    }

    public void setImported(boolean imported) {
        this.imported = imported;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(" ");
        result.append(name);
        return result.toString();
    }
}
