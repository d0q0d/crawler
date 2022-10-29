package com.github.alirezagolshan.crawler.api.dto;

import javax.validation.constraints.NotBlank;

public class AirportCodeInputModel {

    @NotBlank
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
