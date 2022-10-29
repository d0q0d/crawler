package com.github.alirezagolshan.crawler.api.adapter.mapper;


import com.github.alirezagolshan.crawler.api.dto.ConfigResponseModel;
import com.github.alirezagolshan.crawler.model.Config;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConfigAdapterMapper {
    ConfigAdapterMapper INSTANCE = Mappers.getMapper(ConfigAdapterMapper.class);
    ConfigResponseModel getConfigResponseModelFromConfig(Config config);
}
