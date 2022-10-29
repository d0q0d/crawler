package com.github.alirezagolshan.crawler.repository;

import com.github.alirezagolshan.crawler.model.AirportCode;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AirportCodeRepository extends MongoRepository<AirportCode, String> {
  List<AirportCode> findAll();
  Optional<AirportCode> findByCode(String code);
}
