package com.github.alirezagolshan.crawler.service.model.secondservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondServiceApiKey {

  private String searchId;

  public String getSearchId() {
    return searchId;
  }

  public void setSearchId(String searchId) {
    this.searchId = searchId;
  }
}
