package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceOriginAirport {
    @JsonProperty("CityFa")
    private String departureCity;

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }
}
