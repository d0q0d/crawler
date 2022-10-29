package com.github.alirezagolshan.crawler.api.adapter;

import com.github.alirezagolshan.crawler.api.dto.AirportCodeInputModel;
import com.github.alirezagolshan.crawler.model.AirportCode;
import com.github.alirezagolshan.crawler.service.AirportCodeService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportCodeAdapter {

  private final AirportCodeService service;

  public AirportCodeAdapter(AirportCodeService service) {
    this.service = service;
  }

  public List<AirportCode> getAll() {
    return service.getAll();
  }

  public void save(AirportCodeInputModel airportCodeInputModel) {
    service.save(new AirportCode(airportCodeInputModel.getCode()));
  }

  public void delete(String id) {
    service.delete(id);
  }
}
