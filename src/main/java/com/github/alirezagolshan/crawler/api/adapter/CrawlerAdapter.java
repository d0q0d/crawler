package com.github.alirezagolshan.crawler.api.adapter;

import com.github.alirezagolshan.crawler.api.adapter.mapper.CrawlerAdapterMapper;
import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.api.dto.FlightResponseModel;
import com.github.alirezagolshan.crawler.service.CrawlerService;
import org.springframework.stereotype.Component;

@Component
public class CrawlerAdapter {

  private final CrawlerService crawlerService;
  private final CrawlerAdapterMapper mapper;

  public CrawlerAdapter(CrawlerService crawlerService) {
    this.crawlerService = crawlerService;
    this.mapper = CrawlerAdapterMapper.INSTANCE;
  }

  public FlightResponseModel getFreshFlightData(FlightRequestModel requestModel) {
    var flightOutputModel = crawlerService.getFreshFlightData(requestModel);
    var flightDataList = crawlerService.processFlightData(requestModel, flightOutputModel.getProviderFlightsMap());
    return mapper.getFlightResponseModelFromFlightOutputModel(flightDataList);
  }

  public FlightResponseModel getFlightData(FlightRequestModel requestModel) {
    var flightOutputModel = crawlerService.getFlightData(requestModel);
    return mapper.getFlightResponseModelFromFlightOutputModel(flightOutputModel);
  }
}
