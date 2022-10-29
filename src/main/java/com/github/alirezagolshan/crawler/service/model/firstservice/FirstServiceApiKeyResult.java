package com.github.alirezagolshan.crawler.service.model.firstservice;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FirstServiceApiKeyResult {

  @JsonProperty("requestId")
  private String requestId;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }
}
