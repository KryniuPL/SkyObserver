package com.skyobserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Airport {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long id;
  private String ident;
  private String type;
  private String name;
  private double latitudeDeg;
  private double longitudeDeg;
  private long elevationFt;
  private String continent;
  private String isoCountry;
  private String isoRegion;
  private String municipality;
  private String scheduledService;
  private String gpsCode;
  private String iataCode;
  private String localCode;
  private String homeLink;
  private String wikipediaLink;
  private String keywords;

  public Airport(){

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getIdent() {
    return ident;
  }

  public void setIdent(String ident) {
    this.ident = ident;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public double getLatitudeDeg() {
    return latitudeDeg;
  }

  public void setLatitudeDeg(double latitudeDeg) {
    this.latitudeDeg = latitudeDeg;
  }


  public double getLongitudeDeg() {
    return longitudeDeg;
  }

  public void setLongitudeDeg(double longitudeDeg) {
    this.longitudeDeg = longitudeDeg;
  }


  public long getElevationFt() {
    return elevationFt;
  }

  public void setElevationFt(long elevationFt) {
    this.elevationFt = elevationFt;
  }


  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }


  public String getIsoCountry() {
    return isoCountry;
  }

  public void setIsoCountry(String isoCountry) {
    this.isoCountry = isoCountry;
  }


  public String getIsoRegion() {
    return isoRegion;
  }

  public void setIsoRegion(String isoRegion) {
    this.isoRegion = isoRegion;
  }


  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }


  public String getScheduledService() {
    return scheduledService;
  }

  public void setScheduledService(String scheduledService) {
    this.scheduledService = scheduledService;
  }


  public String getGpsCode() {
    return gpsCode;
  }

  public void setGpsCode(String gpsCode) {
    this.gpsCode = gpsCode;
  }


  public String getIataCode() {
    return iataCode;
  }

  public void setIataCode(String iataCode) {
    this.iataCode = iataCode;
  }


  public String getLocalCode() {
    return localCode;
  }

  public void setLocalCode(String localCode) {
    this.localCode = localCode;
  }


  public String getHomeLink() {
    return homeLink;
  }

  public void setHomeLink(String homeLink) {
    this.homeLink = homeLink;
  }


  public String getWikipediaLink() {
    return wikipediaLink;
  }

  public void setWikipediaLink(String wikipediaLink) {
    this.wikipediaLink = wikipediaLink;
  }


  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

}
