package com.github.alirezagolshan.crawler.api.controller;

import com.github.alirezagolshan.crawler.api.dto.RouteRequestModel;
import com.github.alirezagolshan.crawler.api.dto.RouteResponseModel;
import com.github.alirezagolshan.crawler.api.adapter.RouteConfigAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.github.alirezagolshan.crawler.util.Constants.*;

@RestController
@RequestMapping(ROUTE_CONFIG_CONTROLLER_PATH)
@Tag(name = ROUTE_CONFIG_CONTROLLER, description = ROUTE_CONFIG_CONTROLLER_DESCRIPTION)
public class RouteConfigController {

  private final RouteConfigAdapter adapter;

  public RouteConfigController(RouteConfigAdapter adapter) {
    this.adapter = adapter;
  }

  @GetMapping(FLIGHT)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public List<RouteResponseModel> getAllRoutes() {
    return adapter.getAllRoutes();
  }

  @PutMapping(FLIGHT)
  @ResponseStatus(HttpStatus.OK)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public void addRoute(@RequestBody @Valid RouteRequestModel requestModel) {
    adapter.addRoute(requestModel);
  }

  @DeleteMapping(ROUTE_CONFIG_DELETE_PATH)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
  public void deleteRoute(@PathVariable("route-id") String routeId) {
    adapter.deleteRoute(routeId);
  }
}
