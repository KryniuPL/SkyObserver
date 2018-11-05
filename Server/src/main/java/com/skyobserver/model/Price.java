package com.skyobserver.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonFormat
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
