package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.model.AirportCode;
import com.github.alirezagolshan.crawler.repository.AirportCodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirportCodeService {

  private final AirportCodeRepository repository;

  public AirportCodeService(AirportCodeRepository repository) {
    this.repository = repository;
  }

  public List<AirportCode> getAll() {
    return repository.findAll();
  }

  public void save(AirportCode airportCode) {
    if (repository.findByCode(airportCode.getCode()).isEmpty()) repository.save(airportCode);
  }

  public void delete(String id) {
    repository.deleteById(id);
  }
}
