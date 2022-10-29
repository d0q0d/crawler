package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceResult {
  @JsonProperty("PricedItineraries")
  private List<ThirdServicePricedItinerary> pricedItineraries;

  @JsonProperty("OriginDestinationInformations")
  private List<ThirdServiceOriginDestinationInformation> originDestinationInformation;

  public List<ThirdServicePricedItinerary> getPricedItineraries() {
    return pricedItineraries;
  }

  public void setPricedItineraries(List<ThirdServicePricedItinerary> pricedItineraries) {
    this.pricedItineraries = pricedItineraries;
  }

  public List<ThirdServiceOriginDestinationInformation> getOriginDestinationInformation() {
    return originDestinationInformation;
  }

  public void setOriginDestinationInformation(List<ThirdServiceOriginDestinationInformation> originDestinationInformation) {
    this.originDestinationInformation = originDestinationInformation;
  }
}
