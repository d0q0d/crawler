package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightRoute;
import com.github.alirezagolshan.crawler.model.FlightType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class CrawlerScheduler {

  private final CrawlerService crawlerService;
  private final RouteService routeService;

  public CrawlerScheduler(CrawlerService crawlerService, RouteService routeService) {
    this.crawlerService = crawlerService;
    this.routeService = routeService;
  }

  @Scheduled(fixedRate = Long.MAX_VALUE)
  public void scheduler() {
    routeService
        .getAllRoutes()
        .forEach(
            route -> IntStream.rangeClosed(0, route.getDailyRange()).forEach(day ->{
              var requestModel = getFlightRequestModel(route);
              requestModel.setLeaveDate(LocalDate.now().plusDays(day));
              fetchAndSaveFlightData(requestModel);
            }));
  }

  private void fetchAndSaveFlightData(FlightRequestModel requestModel) {
    var freshFlightData = crawlerService.getFreshFlightData(requestModel);
    var processedFlightDataList = crawlerService.processFlightData(requestModel, freshFlightData.getProviderFlightsMap());
    var uniqueTag = UUID.randomUUID().toString();
    processedFlightDataList.forEach(flightData -> flightData.setUniqueTag(uniqueTag));
    crawlerService.saveAllFlightData(processedFlightDataList);
  }

  private FlightRequestModel getFlightRequestModel(FlightRoute route) {
    var flightRequestModel = new FlightRequestModel();
    flightRequestModel.setSourceAirportCode(route.getSourceAirportCode());
    flightRequestModel.setTargetAirportCode(route.getTargetAirportCode());
    flightRequestModel.setAdultCount(1);
    flightRequestModel.setFlightType(FlightType.ECONOMY);
    return flightRequestModel;
  }
}
