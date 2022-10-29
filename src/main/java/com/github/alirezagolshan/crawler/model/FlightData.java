package com.github.alirezagolshan.crawler.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FlightData {

  @Id
  private String id;
  @CreatedDate
  private LocalDateTime createdDate;
  private String uniqueTag;
  private String from;
  private String to;
  private LocalDate departureDate;
  private LocalDate returnDate;
  private String flightNumber;
  private FlightType flightType;
  private Integer firstServiceSeat;
  private String firstServicePrice;
  private Integer secondServiceSeat;
  private String secondServicePrice;
  private Integer thirdServiceSeat;
  private String thirdServicePrice;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }

  public String getUniqueTag() {
    return uniqueTag;
  }

  public void setUniqueTag(String uniqueTag) {
    this.uniqueTag = uniqueTag;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public LocalDate getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(LocalDate departureDate) {
    this.departureDate = departureDate;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDate returnDate) {
    this.returnDate = returnDate;
  }

  public String getFlightNumber() {
    return flightNumber;
  }

  public void setFlightNumber(String flightNumber) {
    this.flightNumber = flightNumber;
  }

  public FlightType getFlightType() {
    return flightType;
  }

  public void setFlightType(FlightType flightType) {
    this.flightType = flightType;
  }

  public Integer getFirstServiceSeat() {
    return firstServiceSeat;
  }

  public void setFirstServiceSeat(Integer firstServiceSeat) {
    this.firstServiceSeat = firstServiceSeat;
  }

  public String getFirstServicePrice() {
    return firstServicePrice;
  }

  public void setFirstServicePrice(String firstServicePrice) {
    this.firstServicePrice = firstServicePrice;
  }

  public Integer getSecondServiceSeat() {
    return secondServiceSeat;
  }

  public void setSecondServiceSeat(Integer secondServiceSeat) {
    this.secondServiceSeat = secondServiceSeat;
  }

  public String getSecondServicePrice() {
    return secondServicePrice;
  }

  public void setSecondServicePrice(String secondServicePrice) {
    this.secondServicePrice = secondServicePrice;
  }

  public Integer getThirdServiceSeat() {
    return thirdServiceSeat;
  }

  public void setThirdServiceSeat(Integer thirdServiceSeat) {
    this.thirdServiceSeat = thirdServiceSeat;
  }

  public String getThirdServicePrice() {
    return thirdServicePrice;
  }

  public void setThirdServicePrice(String thirdServicePrice) {
    this.thirdServicePrice = thirdServicePrice;
  }
}
