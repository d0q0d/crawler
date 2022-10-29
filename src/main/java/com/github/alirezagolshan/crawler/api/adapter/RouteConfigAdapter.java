package com.github.alirezagolshan.crawler.api.adapter;

import com.github.alirezagolshan.crawler.api.adapter.mapper.RouteAdapterMapper;
import com.github.alirezagolshan.crawler.api.dto.RouteRequestModel;
import com.github.alirezagolshan.crawler.api.dto.RouteResponseModel;
import com.github.alirezagolshan.crawler.service.RouteService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteConfigAdapter {

  private final RouteService routeService;
  private final RouteAdapterMapper mapper;

  public RouteConfigAdapter(RouteService routeService) {
    this.routeService = routeService;
    this.mapper = RouteAdapterMapper.INSTANCE;
  }

  public void addRoute(RouteRequestModel requestModel) {
    routeService.addRoute(requestModel);
  }

  public List<RouteResponseModel> getAllRoutes() {
    return routeService.getAllRoutes().stream()
        .map(mapper::getFlightRouteConfigResponseModelFromFlightRouteConfig)
        .collect(Collectors.toList());
  }

  public void deleteRoute(String routeId) {
    routeService.deleteRoute(routeId);
  }
}
