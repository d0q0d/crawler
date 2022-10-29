package com.github.alirezagolshan.crawler.service.model.firstservice;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class FirstServiceResult {

  @JsonProperty("departing")
  private List<FirstServiceFlight> flightList;

  public List<FirstServiceFlight> getFlightList() {
    return flightList;
  }

  public void setFlightList(List<FirstServiceFlight> flightList) {
    this.flightList = flightList;
  }
}
