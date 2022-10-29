package com.github.alirezagolshan.crawler.model;

import org.springframework.data.annotation.Id;

public class AirportCode {

  @Id
  private String id;
  private String code;

  public AirportCode() {}

  public AirportCode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
