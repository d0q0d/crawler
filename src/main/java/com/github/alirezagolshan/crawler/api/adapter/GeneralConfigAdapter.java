package com.github.alirezagolshan.crawler.api.adapter;

import com.github.alirezagolshan.crawler.api.dto.ConfigRequestModel;
import com.github.alirezagolshan.crawler.api.dto.ConfigResponseModel;
import com.github.alirezagolshan.crawler.service.ConfigService;
import com.github.alirezagolshan.crawler.api.adapter.mapper.ConfigAdapterMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GeneralConfigAdapter {

  private final ConfigService configService;
  private final ConfigAdapterMapper mapper;

  public GeneralConfigAdapter(ConfigService configService) {
    this.configService = configService;
    this.mapper = ConfigAdapterMapper.INSTANCE;
  }

  public void update(ConfigRequestModel requestModel) {
    configService.update(requestModel.getKey(), requestModel.getValue());
  }

  public List<ConfigResponseModel> getAll() {
    return configService.getAll().stream()
        .map(mapper::getConfigResponseModelFromConfig)
        .collect(Collectors.toList());
  }
}
