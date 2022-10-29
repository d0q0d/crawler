package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.api.dto.RouteRequestModel;
import com.github.alirezagolshan.crawler.model.FlightRoute;
import com.github.alirezagolshan.crawler.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

  private final RouteRepository repository;

  public RouteService(RouteRepository repository) {
    this.repository = repository;
  }

  public void addRoute(RouteRequestModel requestModel) {
    repository
        .findBySourceAirportCodeAndAndTargetAirportCode(
            requestModel.getSourceAirportCode(), requestModel.getTargetAirportCode())
        .ifPresentOrElse(route -> updateRoute(requestModel, route), () -> saveRoute(requestModel));
  }

  public List<FlightRoute> getAllRoutes() {
    return repository.findAll();
  }

  public void deleteRoute(String routeId) {
    repository.deleteById(routeId);
  }

  private void saveRoute(RouteRequestModel requestModel) {
    repository.save(
        new FlightRoute(
            requestModel.getSourceAirportCode(),
            requestModel.getTargetAirportCode(),
            requestModel.getDailyRange()));
  }

  private void updateRoute(RouteRequestModel requestModel, FlightRoute route) {
    route.setDailyRange(requestModel.getDailyRange());
    repository.save(route);
  }

}
