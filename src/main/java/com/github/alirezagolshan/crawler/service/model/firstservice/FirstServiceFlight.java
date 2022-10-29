package com.github.alirezagolshan.crawler.service.model.firstservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstServiceFlight {

  @JsonProperty("airlineName")
  private String departureAirlineName;

  @JsonProperty("airlineCode")
  private String airlineCode;

  @JsonProperty("leaveDateTime")
  private String departureTime;

  @JsonProperty("originName")
  private String departureCity;

  @JsonProperty("arrivalDateTime")
  private String arrivalTime;

  @JsonProperty("destinationName")
  private String arrivalCity;

  @JsonProperty("flightNumber")
  private String flightNumber;

  @JsonProperty("aircraft")
  private String airplaneName;

  @JsonProperty("priceAdult")
  private String adultTotalPrice;

  @JsonProperty("classType")
  private String classType;

  @JsonProperty("seat")
  private Integer remainSeat;

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

  public Integer getRemainSeat() {
    return remainSeat;
  }

  public void setRemainSeat(Integer remainSeat) {
    this.remainSeat = remainSeat;
  }

  public String getClassType() {
    return classType;
  }

  public void setClassType(String classType) {
    this.classType = classType;
  }

  public String getAirlineCode() {
    return airlineCode;
  }

  public void setAirlineCode(String airlineCode) {
    this.airlineCode = airlineCode;
  }
}
