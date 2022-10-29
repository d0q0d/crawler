package com.github.alirezagolshan.crawler.service.model.secondservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondServiceItem {
  private List<SecondServiceFlight> segments;

  public List<SecondServiceFlight> getSegments() {
    return segments;
  }

  public void setSegments(List<SecondServiceFlight> segments) {
    this.segments = segments;
  }
}
