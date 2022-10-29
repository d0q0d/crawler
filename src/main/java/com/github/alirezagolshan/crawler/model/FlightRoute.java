package com.github.alirezagolshan.crawler.model;

import org.springframework.data.annotation.Id;

public class FlightRoute {

  @Id
  private String id;

  private String sourceAirportCode;

  private String targetAirportCode;

  private Integer dailyRange;

  public FlightRoute(String sourceAirportCode, String targetAirportCode, Integer dailyRange) {
    this.sourceAirportCode = sourceAirportCode;
    this.targetAirportCode = targetAirportCode;
    this.dailyRange = dailyRange;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getSourceAirportCode() {
    return sourceAirportCode;
  }

  public void setSourceAirportCode(String sourceAirportCode) {
    this.sourceAirportCode = sourceAirportCode;
  }

  public String getTargetAirportCode() {
    return targetAirportCode;
  }

  public void setTargetAirportCode(String targetAirportCode) {
    this.targetAirportCode = targetAirportCode;
  }

  public Integer getDailyRange() {
    return dailyRange;
  }

  public void setDailyRange(Integer dailyRange) {
    this.dailyRange = dailyRange;
  }
}
