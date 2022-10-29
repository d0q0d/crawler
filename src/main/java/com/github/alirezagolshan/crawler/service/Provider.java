package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightDataModel;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface Provider {
  @Retryable(value = Exception.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
  FlightDataModel getFlightData(FlightRequestModel flightRequestModel);
}
