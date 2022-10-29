package com.github.alirezagolshan.crawler.service.model.firstservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirstServiceApiKey {

  private FirstServiceApiKeyResult result;

  public FirstServiceApiKeyResult getResult() {
    return result;
  }

  public void setResult(FirstServiceApiKeyResult result) {
    this.result = result;
  }
}
