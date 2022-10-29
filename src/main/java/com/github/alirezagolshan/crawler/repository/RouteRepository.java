package com.github.alirezagolshan.crawler.repository;

import com.github.alirezagolshan.crawler.model.FlightRoute;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends MongoRepository<FlightRoute, String> {
  Optional<FlightRoute> findBySourceAirportCodeAndAndTargetAirportCode(
      String sourceAirportCode, String targetAirportCode);

  List<FlightRoute> findAll();
}
