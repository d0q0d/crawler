package com.github.alirezagolshan.crawler.api.dto;

import java.util.List;

public class FlightResponseModel {

  private List<FlightDataOutputModel> flightDataList;

  public FlightResponseModel() {
  }

  public FlightResponseModel(List<FlightDataOutputModel> flightDataList) {
    this.flightDataList = flightDataList;
  }

  public List<FlightDataOutputModel> getFlightDataList() {
    return flightDataList;
  }

  public void setFlightDataList(List<FlightDataOutputModel> flightDataList) {
    this.flightDataList = flightDataList;
  }
}
