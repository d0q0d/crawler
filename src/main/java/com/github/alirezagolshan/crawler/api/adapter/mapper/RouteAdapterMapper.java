package com.github.alirezagolshan.crawler.api.adapter.mapper;


import com.github.alirezagolshan.crawler.api.dto.RouteResponseModel;
import com.github.alirezagolshan.crawler.model.FlightRoute;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RouteAdapterMapper {
    RouteAdapterMapper INSTANCE = Mappers.getMapper(RouteAdapterMapper.class);
    RouteResponseModel getFlightRouteConfigResponseModelFromFlightRouteConfig(FlightRoute flightRouteConfig);
}
