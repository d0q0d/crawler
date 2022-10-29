package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceOriginDestinationInformation {
    @JsonProperty("OriginAirport")
    private ThirdServiceOriginAirport originAirport;
    @JsonProperty("DestinationAirport")
    private ThirdServiceDestinationAirport destinationAirport;

    public ThirdServiceOriginAirport getOriginAirport() {
        return originAirport;
    }

    public void setOriginAirport(ThirdServiceOriginAirport originAirport) {
        this.originAirport = originAirport;
    }

    public ThirdServiceDestinationAirport getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(ThirdServiceDestinationAirport destinationAirport) {
        this.destinationAirport = destinationAirport;
    }
}
