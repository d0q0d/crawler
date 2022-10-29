package com.github.alirezagolshan.crawler.repository;

import com.github.alirezagolshan.crawler.model.FlightData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FlightDataRepository extends MongoRepository<FlightData, String> {
  List<FlightData> findAllByUniqueTag(String uniqueTag);

  Optional<FlightData> findFirstByFromAndToAndDepartureDateOrderByCreatedDateDesc(
          String sourceAirportCode, String targetAirportCode, LocalDate departureDate);
}
