package com.github.alirezagolshan.crawler.model;

import java.util.List;

public class FlightDataModel {

  private ProviderEnum providers;
  private List<GeneralFlightModel> flightList;

  public FlightDataModel() {}

  public FlightDataModel(ProviderEnum providers, List<GeneralFlightModel> flightList) {
    this.providers = providers;
    this.flightList = flightList;
  }

  public ProviderEnum getProviders() {
    return providers;
  }

  public void setProviders(ProviderEnum providers) {
    this.providers = providers;
  }

  public List<GeneralFlightModel> getFlightList() {
    return flightList;
  }

  public void setFlightList(List<GeneralFlightModel> flightList) {
    this.flightList = flightList;
  }
}
