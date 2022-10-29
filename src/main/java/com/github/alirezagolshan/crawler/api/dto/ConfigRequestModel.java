package com.github.alirezagolshan.crawler.api.dto;

import javax.validation.constraints.NotBlank;

public class ConfigRequestModel {

    @NotBlank
    private String key;
    @NotBlank
    private String value;

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
