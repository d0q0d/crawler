package com.github.alirezagolshan.crawler.config;

import com.github.alirezagolshan.crawler.model.Config;
import com.github.alirezagolshan.crawler.service.ConfigService;
import com.github.alirezagolshan.crawler.service.CrawlerScheduler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.github.alirezagolshan.crawler.util.Constants.CRAWLING_PERIOD;
import static com.github.alirezagolshan.crawler.util.Constants.THIRTY;

@Configuration
public class CustomSchedulingConfigurer implements SchedulingConfigurer {

  private final CrawlerScheduler crawlerScheduler;
  private final ConfigService configService;

  public CustomSchedulingConfigurer(
      CrawlerScheduler crawlerScheduler, ConfigService configService) {
    this.crawlerScheduler = crawlerScheduler;
    this.configService = configService;
  }

  @Override
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
    taskRegistrar.addTriggerTask(
        crawlerScheduler::scheduler,
        triggerContext -> {
          var nextExecutionTime = new GregorianCalendar();
          var lastActualExecutionTime = triggerContext.lastActualExecutionTime();
          nextExecutionTime.setTime(
              lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
          nextExecutionTime.add(Calendar.MINUTE, getFixedRated());
          return nextExecutionTime.getTime();
        });
  }

  private int getFixedRated() {
    return Integer.parseInt(
        configService.getAll().stream()
            .filter(config -> config.getKey().equals(CRAWLING_PERIOD))
            .findFirst()
            .orElse(new Config(CRAWLING_PERIOD, THIRTY))
            .getValue());
  }
}
