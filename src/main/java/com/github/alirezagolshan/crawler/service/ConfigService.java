package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.model.Config;
import com.github.alirezagolshan.crawler.repository.ConfigRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigService {

  private final ConfigRepository configRepository;

  public ConfigService(ConfigRepository configRepository) {
    this.configRepository = configRepository;
  }

  public void update(String key, String value) {
    configRepository
        .findByKey(key)
        .ifPresentOrElse(config -> update(value, config), () -> save(key, value));
  }

  public List<Config> getAll(){
    return configRepository.findAll();
  }

  private void save(String key, String value) {
    configRepository.save(new Config(key, value));
  }

  private void update(String value, Config config) {
    config.setValue(value);
    configRepository.save(config);
  }
}
