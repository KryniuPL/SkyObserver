package com.skyobserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonRootName(value = "price")
@JsonPropertyOrder({ "value", "currency" })
public class Price {

    private double value;
    private String currency;

    @JsonCreator
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
