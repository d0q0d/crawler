package com.github.alirezagolshan.crawler.api.dto;

public class RouteResponseModel {

  private String id;

  private String sourceAirportCode;

  private String targetAirportCode;

  private Integer dailyRange;

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
