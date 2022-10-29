package com.github.alirezagolshan.crawler.api.adapter.mapper;

import com.github.alirezagolshan.crawler.api.dto.FlightDataOutputModel;
import com.github.alirezagolshan.crawler.api.dto.FlightResponseModel;
import com.github.alirezagolshan.crawler.model.FlightData;
import io.jsonwebtoken.lang.Collections;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;


@Mapper
public interface CrawlerAdapterMapper {
  CrawlerAdapterMapper INSTANCE = Mappers.getMapper(CrawlerAdapterMapper.class);

  FlightDataOutputModel flightDataToFlightDataOutputModel(FlightData flightData);

  default FlightResponseModel getFlightResponseModelFromFlightOutputModel(List<FlightData> flightDataList) {
    if (flightDataList != null)
    return new FlightResponseModel(
        flightDataList.stream()
            .map(this::flightDataToFlightDataOutputModel)
            .collect(Collectors.toList()));
    else return new FlightResponseModel(Collections.emptyList());
  }
}
