package com.skyobserver.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonSerialize
public class CachedPrice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String originPointOfRoute;
    private String destinationPointOfRoute;
    private double value;
    private String currency;


    public CachedPrice(String originPointOfRoute, String destinationPointOfRoute, double value, String currency) {
        this.originPointOfRoute = originPointOfRoute;
        this.destinationPointOfRoute = destinationPointOfRoute;
        this.value = value;
        this.currency = currency;
    }

    public String getOriginPointOfRoute() {
        return originPointOfRoute;
    }

    public void setOriginPointOfRoute(String originPointOfRoute) {
        this.originPointOfRoute = originPointOfRoute;
    }

    public String getDestinationPointOfRoute() {
        return destinationPointOfRoute;
    }

    public void setDestinationPointOfRoute(String destinationPointOfRoute) {
        this.destinationPointOfRoute = destinationPointOfRoute;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
