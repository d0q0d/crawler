package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServicePricedItinerary {
  @JsonProperty("FlightSegments")
  private List<ThirdServiceFlight> flightSegments;

  @JsonProperty("DataAttribute")
  private ThirdServiceDataAttribute dataAttribute;

  public List<ThirdServiceFlight> getFlightSegments() {
    return flightSegments;
  }

  public void setFlightSegments(List<ThirdServiceFlight> flightSegments) {
    this.flightSegments = flightSegments;
  }

  public ThirdServiceDataAttribute getDataAttribute() {
    return dataAttribute;
  }

  public void setDataAttribute(ThirdServiceDataAttribute dataAttribute) {
    this.dataAttribute = dataAttribute;
  }
}
