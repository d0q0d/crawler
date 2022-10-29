package com.github.alirezagolshan.crawler.api.controller;

import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.api.dto.FlightResponseModel;
import com.github.alirezagolshan.crawler.api.adapter.CrawlerAdapter;
import com.github.alirezagolshan.crawler.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.github.alirezagolshan.crawler.util.Constants.*;

@RestController
@RequestMapping(CRAWLER_PATH)
@Tag(name = CRAWLER_CONTROLLER, description = CRAWLER_CONTROLLER_DESCRIPTION)
public class CrawlerController {

  private final CrawlerAdapter adapter;

  public CrawlerController(CrawlerAdapter adapter) {
    this.adapter = adapter;
  }

  @GetMapping(CRAWLER_FRESH_DATA_PATH)
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = CRAWLER_FRESH_DATA_SUMMERY, description = CRAWLER_FRESH_DATA_DESCRIPTION)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public FlightResponseModel getFreshFlightData(FlightRequestModel requestModel) {
    return adapter.getFreshFlightData(requestModel);
  }

  @GetMapping(Constants.FLIGHT)
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = CRAWLER_DATA_SUMMERY, description = CRAWLER_DATA_DESCRIPTION)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public FlightResponseModel getFlightData(FlightRequestModel requestModel) {
    return adapter.getFlightData(requestModel);
  }
}
