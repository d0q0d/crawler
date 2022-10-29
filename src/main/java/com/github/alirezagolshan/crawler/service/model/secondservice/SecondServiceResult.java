package com.github.alirezagolshan.crawler.service.model.secondservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecondServiceResult {
  @JsonProperty("items")
  private List<SecondServiceItem> items;

  @JsonProperty("totalChargeable")
  private String adultTotalPrice;

  @JsonProperty("classTypeName")
  private String classType;

  public List<SecondServiceItem> getItems() {
    return items;
  }

  public void setItems(List<SecondServiceItem> items) {
    this.items = items;
  }

  public String getAdultTotalPrice() {
    return adultTotalPrice;
  }

  public void setAdultTotalPrice(String adultTotalPrice) {
    this.adultTotalPrice = adultTotalPrice;
  }

  public String getClassType() {
    return classType;
  }

  public void setClassType(String classType) {
    this.classType = classType;
  }
}
