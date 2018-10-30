package com.skyobserver.model;

public class Price {
    private double value;
    private String currency;

    public Price(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
