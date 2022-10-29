package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightData;
import com.github.alirezagolshan.crawler.model.FlightOutputModel;
import com.github.alirezagolshan.crawler.model.GeneralFlightModel;
import com.github.alirezagolshan.crawler.model.ProviderEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CrawlerService {

  private final FlightService flightService;

  public CrawlerService(FlightService flightService) {
    this.flightService = flightService;
  }

  public FlightOutputModel getFreshFlightData(FlightRequestModel requestModel) {
    return flightService.getFreshFlightData(requestModel);
  }

  public List<FlightData> getFlightData(FlightRequestModel requestModel) {
    return flightService.getFlightData(requestModel);
  }

  public List<FlightData> processFlightData(FlightRequestModel requestModel, Map<ProviderEnum, List<GeneralFlightModel>> flightDataMap) {
    return flightService.processFlightData(requestModel, flightDataMap);
  }

  public void saveAllFlightData(List<FlightData> flightList) {
    flightService.saveAllFlightData(flightList);
  }

}
