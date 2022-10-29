package com.github.alirezagolshan.crawler.model;

public class GeneralFlightModel {

  private String departureAirlineName;
  private String airlineCode;
  private String departureTime;
  private String departureCity;
  private String arrivalTime;
  private String arrivalCity;
  private String flightNumber;
  private String airplaneName;
  private String adultTotalPrice;
  private String provider;
  private String classType;
  private Integer remainSeat;

  public GeneralFlightModel() {
  }

  public String getDepartureAirlineName() {
    return departureAirlineName;
  }

  public void setDepartureAirlineName(String departureAirlineName) {
    this.departureAirlineName = departureAirlineName;
  }

  public String getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  public String getDepartureCity() {
    return departureCity;
  }

  public void setDepartureCity(String departureCity) {
    this.departureCity = departureCity;
  }

  public String getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public String getArrivalCity() {
    return arrivalCity;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public void setArrivalCity(String arrivalCity) {
    this.arrivalCity = arrivalCity;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public String getAirplaneName() {
    return airplaneName;
  }

  public void setAirplaneName(String airplaneName) {
    this.airplaneName = airplaneName;
  }

  public String getAdultTotalPrice() {
    return adultTotalPrice;
  }

  public void setAdultTotalPrice(String adultTotalPrice) {
    this.adultTotalPrice = adultTotalPrice;
  }

  public String getClassType() {
    return classType;
  }

  public void setClassType(String classType) {
    this.classType = classType;
  }

  public Integer getRemainSeat() {
    return remainSeat;
  }

  public void setRemainSeat(Integer remainSeat) {
    this.remainSeat = remainSeat;
  }

  public String getAirlineCode() {
    return airlineCode;
  }

  public void setAirlineCode(String airlineCode) {
    this.airlineCode = airlineCode;
  }
}
