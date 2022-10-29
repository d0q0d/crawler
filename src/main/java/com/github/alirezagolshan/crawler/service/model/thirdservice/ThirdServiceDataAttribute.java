package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceDataAttribute {
    @JsonProperty("PerAdult")
    private ThirdServicePerAdult perAdult;

    public ThirdServicePerAdult getPerAdult() {
        return perAdult;
    }

    public void setPerAdult(ThirdServicePerAdult perAdult) {
        this.perAdult = perAdult;
    }
}
