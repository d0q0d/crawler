package com.github.alirezagolshan.crawler.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHiddenInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.github.alirezagolshan.crawler.model.*;
import com.github.alirezagolshan.crawler.service.mapper.CrawlerServiceMapper;
import com.github.mfathi91.time.PersianDate;
import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.service.model.thirdservice.ThirdServiceResponse;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.github.alirezagolshan.crawler.model.ProviderEnum.THIRD_SERVICE;

@Component
public class ThirdService implements Provider {

  private final OkHttpClient okHttpClient;
  private final ObjectMapper objectMapper;
  private final Logger logger;
  private final CrawlerServiceMapper mapper;

  public ThirdService(OkHttpClient okHttpClient, ObjectMapper objectMapper, Logger logger) {
    this.okHttpClient = okHttpClient;
    this.objectMapper = objectMapper;
    this.logger = logger;
    this.mapper = CrawlerServiceMapper.INSTANCE;
  }

  @Override
  public FlightDataModel getFlightData(FlightRequestModel flightRequestModel) {
    var body = getRequestBody(flightRequestModel);
    var request = getRequest(body);
    try {
      var response = okHttpClient.newCall(request).execute();
      var rawResponse = response.body().string();
      var thirdServiceResponse = objectMapper.readValue(rawResponse, ThirdServiceResponse.class);
      var generalFlightList = getGeneralFlightModel(thirdServiceResponse, flightRequestModel);
      return new FlightDataModel(THIRD_SERVICE, generalFlightList);
    } catch (Exception e) {
      logger.log(Level.SEVERE, "Third service Error", e);
      return new FlightDataModel(ProviderEnum.THIRD_SERVICE, new ArrayList<>());
    }
  }

  private Request getRequest(RequestBody body) {
    return new Request.Builder()
            .url("Put your main API URL here")
            .method("POST", body)
            .addHeader("accept", "*/*")
            .addHeader("accept-language", "en-US,en;q=0.9")
            .addHeader("content-type", "application/x-www-form-urlencoded; charset=UTF-8")
            .addHeader(
                    "user-agent",
                    "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36")
            .build();
  }

  private RequestBody getRequestBody(FlightRequestModel flightRequestModel) {
    return RequestBody.create(
            MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8"),
            "ReturnUrl=&__RequestVerificationToken="
                    + getRequestVerificationToken()
                    + "OriginLocationCodes%5B0%5D="
                    + flightRequestModel.getSourceAirportCode()
                    + "%2C1&DestinationLocationCodes%5B0%5D="
                    + flightRequestModel.getTargetAirportCode()
                    + "%2C1&DepartureDateTimes%5B0%5D="
                    + PersianDate.fromGregorian(flightRequestModel.getLeaveDate())
                    + "&DepartureDateTimes_Lang%5B0%5D=&AdultCount="
                    + flightRequestModel.getAdultCount()
                    + "&ChildCount="
                    + flightRequestModel.getChildCount()
                    + "&InfantCount="
                    + flightRequestModel.getInfantCount()
                    + "&CabinType=" + getCabinType(flightRequestModel) + "&Domestic=&Domestic_FlightType="
                    + getTripType(flightRequestModel)
                    + "&StepDays=0");
  }

  private String getCabinType(FlightRequestModel flightRequestModel) {
    return flightRequestModel.getFlightType().equals(FlightType.ECONOMY) || flightRequestModel.getFlightType() == null ? "1" : "3";
  }

  private String getRequestVerificationToken() {
    try (var webClient = new WebClient()) {
      webClient.getOptions().setJavaScriptEnabled(false);
      HtmlPage page = null;
      try {page = webClient.getPage("Put the third service home page URL here!");}
      catch (IOException e) {logger.log(Level.SEVERE, "Third service Error", e);}
      var verificationTokenHiddenInput = (HtmlHiddenInput) page.getElementByName("__RequestVerificationToken");
      return verificationTokenHiddenInput.getValueAttribute() + "&";
    }
  }

  private String getTripType(FlightRequestModel flightRequestModel) {
    return flightRequestModel.getTripType().equals(TripType.ONE_WAY) ? "OneWay" : "Return";
  }

  private List<GeneralFlightModel> getGeneralFlightModel(ThirdServiceResponse thirdServiceResponse, FlightRequestModel flightRequestModel) {
    if (thirdServiceResponse.getResult() == null) throw new IllegalStateException();
    var generalFlightModelList = mapper.getGeneralFlightModelFromThirdServiceResponse(thirdServiceResponse, flightRequestModel);
    return generalFlightModelList.stream()
        .peek(this::normalizeThirdServiceResponse)
        .sorted(Comparator.comparing(item -> Integer.parseInt(item.getAdultTotalPrice())))
        .collect(Collectors.toList());
  }

  private void normalizeThirdServiceResponse(GeneralFlightModel flightResponse) {
    flightResponse.setAdultTotalPrice(
        flightResponse
            .getAdultTotalPrice()
            .substring(0, flightResponse.getAdultTotalPrice().indexOf(".") - 1));
    flightResponse.setProvider(THIRD_SERVICE.name());
  }

}
