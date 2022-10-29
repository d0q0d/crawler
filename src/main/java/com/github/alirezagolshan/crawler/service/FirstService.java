package com.github.alirezagolshan.crawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alirezagolshan.crawler.service.mapper.CrawlerServiceMapper;
import com.github.alirezagolshan.crawler.service.model.firstservice.FirstServiceApiKey;
import com.github.alirezagolshan.crawler.service.model.firstservice.FirstServiceResponse;
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

import static com.github.alirezagolshan.crawler.model.ProviderEnum.FIRST_PROVIDER;

@Component
public class FirstService implements Provider {

  private final OkHttpClient okHttpClient;
  private final ObjectMapper objectMapper;
  private final Logger logger;
  private final CrawlerServiceMapper mapper;

  public FirstService(OkHttpClient okHttpClient, ObjectMapper objectMapper, Logger logger) {
    this.okHttpClient = okHttpClient;
    this.objectMapper = objectMapper;
    this.logger = logger;
    this.mapper = CrawlerServiceMapper.INSTANCE;
  }

  @Override
  public FlightDataModel getFlightData(FlightRequestModel flightRequestModel) {
    var apiKeyRequestBody = getApiKeyRequestBody(flightRequestModel);
    var apiKeyRequest = getApiKeyRequest(apiKeyRequestBody);
    var requestId = "";
    try {
      requestId = getRequestId(apiKeyRequest);
      var firstServiceResponse = getFirstServiceResponse(requestId);
      var generalFlightList = getGeneralFlightModel(firstServiceResponse);
      return new FlightDataModel(FIRST_PROVIDER, filterBasedOnFlightType(generalFlightList, flightRequestModel.getFlightType()));
    } catch (Exception e) {
      logger.log(Level.SEVERE,"First service error", e);
      return new FlightDataModel(FIRST_PROVIDER, new ArrayList<>());
    }
  }

  private RequestBody getApiKeyRequestBody(FlightRequestModel flightRequestModel) {
    var content =
            "{\"origin\":"
                    + "\""
                    + flightRequestModel.getSourceAirportCode()
                    + "\""
                    + ","
                    + "\"destination\":"
                    + "\""
                    + flightRequestModel.getTargetAirportCode()
                    + "\""
                    + ","
                    + "\"departureDate\":"
                    + "\""
                    + flightRequestModel.getLeaveDate().toString()
                    + "\""
                    + ","
                    + "\"adult\":"
                    + "\""
                    + flightRequestModel.getAdultCount()
                    + "\""
                    + ","
                    + "\"child\":"
                    + "\""
                    + flightRequestModel.getChildCount()
                    + "\""
                    + ","
                    + "\"infant\":"
                    + "\""
                    + flightRequestModel.getInfantCount()
                    + "\""
                    + "}";
    if (flightRequestModel.getReturnDate() != null) {
      content =
              content.substring(0, content.length() - 1)
                      + ","
                      + "\"departureDate\":"
                      + "\""
                      + flightRequestModel.getReturnDate().toString()
                      + "\""
                      + "}";
    }
    return RequestBody.create(MediaType.parse("application/json"), content);
  }

  private Request getApiKeyRequest(RequestBody requestBody) {
    return new Request.Builder()
            .url("Put your API key URL here")
            .method("POST", requestBody)
            .addHeader("Accept", "application/json, text/plain, */*")
            .addHeader("Accept-Language", "en-US,en;q=0.5")
            .build();
  }

  private Request getFlightInformationRequest(String requestId) {
    return new Request.Builder()
        .url("Put your main API URL here" + requestId)
        .method("GET", null)
        .addHeader("Accept", "application/json, text/plain, */*")
        .addHeader("Accept-Language", "en-US,en;q=0.5")
        .build();
  }

  private String getRequestId(Request apiKeyRequest) throws IOException {
    var response = okHttpClient.newCall(apiKeyRequest).execute();
    var apiKeyResponse = response.body().string();
    var firstServiceApiKey = objectMapper.readValue(apiKeyResponse, FirstServiceApiKey.class);
    return firstServiceApiKey.getResult().getRequestId();
  }

  private FirstServiceResponse getFirstServiceResponse(String requestId) throws IOException {
    var flightInformationRequest = getFlightInformationRequest(requestId);
    var flightResponse = okHttpClient.newCall(flightInformationRequest).execute();
    var flightInformationResponse = flightResponse.body().string();
    return objectMapper.readValue(flightInformationResponse, FirstServiceResponse.class);
  }

  private List<GeneralFlightModel> getGeneralFlightModel(FirstServiceResponse firstServiceResponse) {
    if (firstServiceResponse.getResult() == null) throw new IllegalStateException();
    return firstServiceResponse.getResult().getFlightList().stream()
        .map(mapper::getGeneralFlightModelFromFirstServiceResponseWithClassType)
        .peek(this::normalizeFirstServiceResponse)
        .sorted(Comparator.comparing(item -> Integer.valueOf(item.getAdultTotalPrice())))
        .collect(Collectors.toList());
  }

  private void normalizeFirstServiceResponse(GeneralFlightModel flightResponse) {
    flightResponse.setAdultTotalPrice(
        flightResponse
            .getAdultTotalPrice()
            .substring(0, flightResponse.getAdultTotalPrice().length() - 1));
    flightResponse.setDepartureTime(
        LocalDateTime.parse(flightResponse.getDepartureTime()).toLocalTime().toString());

    flightResponse.setArrivalTime(
        LocalDateTime.parse(flightResponse.getArrivalTime()).toLocalTime().toString());

    flightResponse.setProvider(FIRST_PROVIDER.name());
  }

  // the API doesn't accept these values
  private List<GeneralFlightModel> filterBasedOnFlightType(List<GeneralFlightModel> generalFlightList, FlightType flightType) {
    if (flightType == null || flightType.equals(FlightType.ECONOMY))
      return generalFlightList.stream().filter(generalFlightModel -> generalFlightModel.getClassType().equals("Economy")).collect(Collectors.toList());
    else return generalFlightList.stream().filter(generalFlightModel -> generalFlightModel.getClassType().equals("Business")).collect(Collectors.toList());
  }

}
