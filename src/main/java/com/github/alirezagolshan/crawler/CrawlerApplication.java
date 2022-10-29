package com.github.alirezagolshan.crawler;

import org.apache.xpath.operations.String;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.github.alirezagolshan.crawler.util.Utils.uname;

@EnableRetry
@EnableScheduling
@EnableMongoAuditing
@SpringBootApplication
public class CrawlerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CrawlerApplication.class, args);
    uname();
  }
}
