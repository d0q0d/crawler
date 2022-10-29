package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceResponse {
  private ThirdServiceResult result;

  public ThirdServiceResult getResult() {
    return result;
  }

  public void setResult(ThirdServiceResult result) {
    this.result = result;
  }
}
