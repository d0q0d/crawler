package com.github.alirezagolshan.crawler.service.model.secondservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondServiceResponse {
  private List<SecondServiceResult> items;

  public List<SecondServiceResult> getItems() {
    return items;
  }

  public void setItems(List<SecondServiceResult> items) {
    this.items = items;
  }

}
