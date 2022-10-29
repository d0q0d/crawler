package com.github.alirezagolshan.crawler.repository;

import com.github.alirezagolshan.crawler.model.Config;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ConfigRepository extends MongoRepository<Config, String> {
  Optional<Config> findByKey(String key);
  List<Config> findAll();
}
