package com.idird.taxcalculator.domain.invoice;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Collectors;

public class Invoice {

    private Collection<Purchase> purchases;
    private BigDecimal taxAmout;
    private BigDecimal totalAmount;


    public Invoice(Collection<Purchase> p_purchases, BigDecimal p_taxAmout, BigDecimal p_totalAmount) {
        this.purchases = p_purchases;
        this.taxAmout = p_taxAmout;
        this.totalAmount = p_totalAmount;
    }

    public Collection<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(Collection<Purchase> purchases) {
        this.purchases = purchases;
    }

    public BigDecimal getTaxAmout() {
        return taxAmout;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append(purchases.stream().map(Purchase::toString).collect(Collectors.joining("\n")));
        if (taxAmout != null) {
            result.append("\n");
            result.append("Montant des taxes : " + taxAmout);
        }
        if (totalAmount != null) {
            result.append("\n");
            result.append("Total : " + totalAmount);
        }
        return result.toString();
    }
}
