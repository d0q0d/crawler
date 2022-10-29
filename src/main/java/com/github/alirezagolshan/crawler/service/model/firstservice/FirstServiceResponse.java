package com.github.alirezagolshan.crawler.service.model.firstservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirstServiceResponse {

  private FirstServiceResult result;

  public FirstServiceResult getResult() {
    return result;
  }

  public void setResult(FirstServiceResult result) {
    this.result = result;
  }
}
