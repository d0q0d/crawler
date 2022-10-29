package com.github.alirezagolshan.crawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alirezagolshan.crawler.service.mapper.CrawlerServiceMapper;
import com.github.alirezagolshan.crawler.service.model.secondservice.SecondServiceApiKey;
import com.github.alirezagolshan.crawler.service.model.secondservice.SecondServiceResponse;
import com.github.alirezagolshan.crawler.service.model.secondservice.SecondServiceResult;
import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightDataModel;
import com.github.alirezagolshan.crawler.model.FlightType;
import com.github.alirezagolshan.crawler.model.GeneralFlightModel;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.github.alirezagolshan.crawler.model.ProviderEnum.SECOND_SERVICE;

@Component
public class SecondService implements Provider {

  private final OkHttpClient okHttpClient;
  private final ObjectMapper objectMapper;
  private final Logger logger;
  private final CrawlerServiceMapper mapper;

  public SecondService(OkHttpClient okHttpClient, ObjectMapper objectMapper, Logger logger) {
    this.okHttpClient = okHttpClient;
    this.objectMapper = objectMapper;
    this.logger = logger;
    this.mapper = CrawlerServiceMapper.INSTANCE;
  }

  @Override
  public FlightDataModel getFlightData(FlightRequestModel flightRequestModel) {
    var apiKeyRequestBody = getApiKeyRequestBody(flightRequestModel);
    var apiKeyRequest = getApiKeyRequest(apiKeyRequestBody);
    try {
      var requestId = getRequestId(apiKeyRequest);
      var secondServiceResponse = getSecondServiceResponse(requestId);
      var generalFlightList = getGeneralFlightModel(secondServiceResponse);
      return new FlightDataModel(SECOND_SERVICE, filterBasedOnFlightType(generalFlightList, flightRequestModel.getFlightType()));
    } catch (Exception e) {
      logger.log(Level.SEVERE,"Second service Error", e);
      return new FlightDataModel(SECOND_SERVICE, new ArrayList<>());
    }
  }

  private RequestBody getApiKeyRequestBody(FlightRequestModel flightRequestModel) {
    var tripMode = 1;
    var content =
            "{\"source\":"
                    + "\""
                    + flightRequestModel.getSourceAirportCode()
                    + "\""
                    + ","
                    + "\"sourceLabel\":"
                    + "\""
                    + flightRequestModel.getSourceAirportCode()
                    + "\""
                    + ","
                    + "\"flightType\":"
                    + 2
                    + ","
                    + "\"dest\":"
                    + "\""
                    + flightRequestModel.getTargetAirportCode()
                    + "\""
                    + ","
                    + "\"destLabel\":"
                    + "\""
                    + flightRequestModel.getTargetAirportCode()
                    + "\""
                    + ","
                    + "\"isExtraInfoRequested\":"
                    + false
                    + ","
                    + "\"depart\":"
                    + "\""
                    + flightRequestModel.getLeaveDate().toString()
                    + "\""
                    + ","
                    + "\"adult\":"
                    + flightRequestModel.getAdultCount()
                    + ","
                    + "\"child\":"
                    + flightRequestModel.getChildCount()
                    + ","
                    + "\"infant\":"
                    + flightRequestModel.getInfantCount()
                    + "}";
    if (flightRequestModel.getReturnDate() != null) {
      tripMode = 2;
      content =
              content.substring(0, content.length() - 1)
                      + ","
                      + "\"return\":"
                      + "\""
                      + flightRequestModel.getReturnDate().toString()
                      + "\""
                      + "}";
    }
    content = content.substring(0, content.length() - 1) + "," + "\"tripMode\":" + tripMode + "}";
    return RequestBody.create(MediaType.parse("application/json"), content);
  }

  private Request getApiKeyRequest(RequestBody requestBody) {
    return new Request.Builder()
            .url("Put your API key URL here")
            .method("POST", requestBody)
            .addHeader("accept", "application/json, text/javascript, text/plain, text/html, application/vnd.ms-excel")
            .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")
            .build();
  }

  private SecondServiceResponse getSecondServiceResponse(String requestId) throws IOException {
    var flightInformationRequest = getFlightInformationRequest(requestId);
    var flightResponse = okHttpClient.newCall(flightInformationRequest).execute();
    return objectMapper.readValue(flightResponse.body().string(), SecondServiceResponse.class);
  }

  private String getRequestId(Request apiKeyRequest) throws IOException {
    var response = okHttpClient.newCall(apiKeyRequest).execute();
    var apiKeyResponse = response.body().string();
    var flightioApiKey = objectMapper.readValue(apiKeyResponse, SecondServiceApiKey.class);
    return flightioApiKey.getSearchId();
  }

  private Request getFlightInformationRequest(String requestId) {
    return new Request.Builder()
        .url("Put your main API URL here")
        .method("GET", null)
        .addHeader("accept", "application/json, text/javascript, text/plain, text/html, application/vnd.ms-excel")
        .addHeader("user-agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")
        .build();
  }

  private List<GeneralFlightModel> getGeneralFlightModel(SecondServiceResponse secondServiceResponse) {
    var flightioResultList = getSortedFlightResultList(secondServiceResponse);
    secondServiceResponse.setItems(flightioResultList);
    return getGeneralFlightList(secondServiceResponse);
  }

  private List<GeneralFlightModel> getGeneralFlightList(SecondServiceResponse secondServiceResponse) {
    return secondServiceResponse.getItems().stream()
        .map(
            item ->
                mapper.getGeneralFlightModelFromSecondResponse(
                    item.getItems().get(0).getSegments().get(0), item.getAdultTotalPrice(), item.getClassType()))
        .peek(
            flightResponse -> {
              flightResponse.setDepartureTime(
                  LocalDateTime.parse(flightResponse.getDepartureTime()).toLocalTime().toString());

              flightResponse.setArrivalTime(
                  LocalDateTime.parse(flightResponse.getArrivalTime()).toLocalTime().toString());

              flightResponse.setProvider(SECOND_SERVICE.name());
            })
        .collect(Collectors.toList());
  }

  private List<SecondServiceResult> getSortedFlightResultList(SecondServiceResponse secondServiceResponse) {
    return secondServiceResponse.getItems().stream()
        .peek(this::normalizeSecondServiceResponse)
        .sorted(Comparator.comparing(firstItem -> Integer.valueOf(firstItem.getAdultTotalPrice())))
        .collect(Collectors.toList());
  }

  private void normalizeSecondServiceResponse(SecondServiceResult item) {
    item.setAdultTotalPrice(
        item.getAdultTotalPrice()
            .substring(0, item.getAdultTotalPrice().indexOf(".") - 1));
  }

  // the API doesn't accept these values
  private List<GeneralFlightModel> filterBasedOnFlightType(List<GeneralFlightModel> generalFlightList, FlightType flightType) {
    if (flightType == null ||flightType.equals(FlightType.ECONOMY) )
      return generalFlightList.stream().filter(generalFlightModel -> generalFlightModel.getClassType().contains("Economy")).collect(Collectors.toList());
    else return generalFlightList.stream().filter(generalFlightModel -> generalFlightModel.getClassType().contains("Business")).collect(Collectors.toList());
  }

}
