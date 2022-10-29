package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServicePerAdult {
    @JsonProperty("TotalFareWithMarkupAndVat")
    private String adultTotalPrice;

    public String getAdultTotalPrice() {
        return adultTotalPrice;
    }

    public void setAdultTotalPrice(String adultTotalPrice) {
        this.adultTotalPrice = adultTotalPrice;
    }
}
