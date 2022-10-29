package com.github.alirezagolshan.crawler.api.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RouteRequestModel {

  @NotNull
  private String sourceAirportCode;

  @NotNull
  private String targetAirportCode;

  @NotNull
  @Min(1)
  private Integer dailyRange;

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
