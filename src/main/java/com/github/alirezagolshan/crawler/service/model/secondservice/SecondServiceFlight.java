package com.github.alirezagolshan.crawler.service.model.secondservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondServiceFlight {

  @JsonProperty("operatingAirlineNamePersian")
  private String departureAirlineName;

  @JsonProperty("departTime")
  private String departureTime;

  @JsonProperty("originCityNameFa")
  private String departureCity;

  @JsonProperty("operatingAirlineCode")
  private String airlineCode;

  @JsonProperty("arrivalTime")
  private String arrivalTime;

  @JsonProperty("destnationCityNameFa")
  private String arrivalCity;

  @JsonProperty("flightNumber")
  private String flightNumber;

  @JsonProperty("aircraftName")
  private String airplaneName;

  @JsonProperty("originAirportCode")
  private String departureAirportName;

  @JsonProperty("destinationAirportCode")
  private String arrivalAirportName;

  @JsonProperty("availableSeat")
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

  public String getDepartureAirportName() {
    return departureAirportName;
  }

  public void setDepartureAirportName(String departureAirportName) {
    this.departureAirportName = departureAirportName;
  }

  public String getArrivalAirportName() {
    return arrivalAirportName;
  }

  public void setArrivalAirportName(String arrivalAirportName) {
    this.arrivalAirportName = arrivalAirportName;
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
