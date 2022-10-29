package com.github.alirezagolshan.crawler.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FlightOutputModel {

  private Map<ProviderEnum, List<GeneralFlightModel>> providerFlightsMap;
  private LocalDateTime createdDate;

  public FlightOutputModel() {}

  public FlightOutputModel(Map<ProviderEnum, List<GeneralFlightModel>> providerFlightsMap, LocalDateTime createdDate) {
    this.providerFlightsMap = providerFlightsMap;
    this.createdDate = createdDate;
  }

  public Map<ProviderEnum, List<GeneralFlightModel>> getProviderFlightsMap() {
    return providerFlightsMap;
  }

  public void setProviderFlightsMap(
      Map<ProviderEnum, List<GeneralFlightModel>> providerFlightsMap) {
    this.providerFlightsMap = providerFlightsMap;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDateTime createdDate) {
    this.createdDate = createdDate;
  }
}
