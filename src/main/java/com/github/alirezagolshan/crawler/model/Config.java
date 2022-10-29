package com.github.alirezagolshan.crawler.model;

import org.springframework.data.annotation.Id;

public class Config {
  @Id
  private String id;
  private String key;
  private String value;

  public Config(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
