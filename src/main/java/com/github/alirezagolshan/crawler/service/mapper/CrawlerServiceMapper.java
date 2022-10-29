package com.github.alirezagolshan.crawler.service.mapper;

import com.github.alirezagolshan.crawler.service.model.firstservice.FirstServiceFlight;
import com.github.alirezagolshan.crawler.service.model.secondservice.SecondServiceFlight;
import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightData;
import com.github.alirezagolshan.crawler.model.FlightType;
import com.github.alirezagolshan.crawler.model.GeneralFlightModel;
import com.github.alirezagolshan.crawler.model.ProviderEnum;
import com.github.alirezagolshan.crawler.service.model.thirdservice.ThirdServiceOriginDestinationInformation;
import com.github.alirezagolshan.crawler.service.model.thirdservice.ThirdServicePricedItinerary;
import com.github.alirezagolshan.crawler.service.model.thirdservice.ThirdServiceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.*;

import static java.util.Map.entry;

@Mapper
public interface CrawlerServiceMapper {
  CrawlerServiceMapper INSTANCE = Mappers.getMapper(CrawlerServiceMapper.class);

  GeneralFlightModel getGeneralFlightModelFromFirstServiceResponse(FirstServiceFlight firstServiceFlight);

  GeneralFlightModel getGeneralFlightModelFromSecondResponse(SecondServiceFlight secondServiceFlight, String adultTotalPrice, String classType);

  default GeneralFlightModel getGeneralFlightModelFromFirstServiceResponseWithClassType(FirstServiceFlight firstServiceFlight){
    var generalFlightModel = getGeneralFlightModelFromFirstServiceResponse(firstServiceFlight);
    if (firstServiceFlight.getClassType().equals("E")) generalFlightModel.setClassType("Economy");
    else generalFlightModel.setClassType("Business");
    return generalFlightModel;
  }

  default List<GeneralFlightModel> getGeneralFlightModelFromThirdServiceResponse(ThirdServiceResponse thirdServiceResponse, FlightRequestModel flightRequestModel) {
    var generalFlightList = new ArrayList<GeneralFlightModel>();
    var firstServiceOriginDestinationInformation = thirdServiceResponse.getResult().getOriginDestinationInformation().get(0);
    thirdServiceResponse
        .getResult()
        .getPricedItineraries()
        .forEach(thirdServicePricedItinerary -> setThirdServiceFlightData(generalFlightList, thirdServicePricedItinerary, flightRequestModel));
    generalFlightList.forEach(generalFlightModel -> setThirdServiceCitiesData(firstServiceOriginDestinationInformation, generalFlightModel));
    return generalFlightList;
  }

  private void setThirdServiceCitiesData(ThirdServiceOriginDestinationInformation thirdServiceOriginDestinationInformation, GeneralFlightModel generalFlightModel) {
    generalFlightModel.setDepartureCity(thirdServiceOriginDestinationInformation.getOriginAirport().getDepartureCity());
    generalFlightModel.setArrivalCity(thirdServiceOriginDestinationInformation.getDestinationAirport().getArrivalCity());
  }

  private void setThirdServiceFlightData(ArrayList<GeneralFlightModel> generalFlightList, ThirdServicePricedItinerary thirdServicePricedItinerary, FlightRequestModel flightRequestModel) {
    var generalFlightModel = new GeneralFlightModel();
    var thirdServiceFlight = thirdServicePricedItinerary.getFlightSegments().get(0);
    generalFlightModel.setFlightNumber(thirdServiceFlight.getFlightNumber());
    generalFlightModel.setAirlineCode(thirdServiceFlight.getAirlineCode());
    generalFlightModel.setArrivalTime(thirdServiceFlight.getArrivalTime());
    generalFlightModel.setDepartureTime(thirdServiceFlight.getDepartureTime());
    generalFlightModel.setDepartureAirlineName(thirdServiceFlight.getFlightNumber());
    generalFlightModel.setRemainSeat(thirdServiceFlight.getRemainSeat());
    generalFlightModel.setDepartureAirlineName(thirdServiceAirlineMap.get(thirdServiceFlight.getDepartureAirportName()));
    generalFlightModel.setAdultTotalPrice(thirdServicePricedItinerary.getDataAttribute().getPerAdult().getAdultTotalPrice());
    generalFlightModel.setAirplaneName("unavailable");
    if (flightRequestModel == null || flightRequestModel.getFlightType().equals(FlightType.ECONOMY)) generalFlightModel.setClassType("Economy");
    else generalFlightModel.setClassType("Business");
    generalFlightList.add(generalFlightModel);
  }

  default void setValuesBasedOnRequestModel(FlightData flightData, FlightRequestModel requestModel, String flightNumber, List<GeneralFlightModel> generalFlightList){
    setGeneralFlightInformation(flightData, requestModel, flightNumber, generalFlightList);
    setProviderFlightInformation(flightData, generalFlightList);
  }

  private void setProviderFlightInformation(FlightData flightData, List<GeneralFlightModel> generalFlightList) {
    setFirstServiceInformation(flightData, generalFlightList);
    setSecondServiceInformation(flightData, generalFlightList);
    setThirdServiceInformation(flightData, generalFlightList);
  }

  private void setGeneralFlightInformation(FlightData flightData, FlightRequestModel requestModel, String flightNumber, List<GeneralFlightModel> generalFlightList) {
    flightData.setFrom(requestModel.getSourceAirportCode());
    flightData.setTo(requestModel.getTargetAirportCode());
    flightData.setDepartureDate(requestModel.getLeaveDate());
    flightData.setReturnDate(requestModel.getReturnDate());
    flightData.setFlightType(requestModel.getFlightType());
    if (generalFlightList.get(0) != null && generalFlightList.get(0).getAirlineCode() != null)
      flightData.setFlightNumber(generalFlightList.get(0).getAirlineCode() + flightNumber);
    else flightData.setFlightNumber(flightNumber);
  }

  private void setThirdServiceInformation(FlightData flightData, List<GeneralFlightModel> generalFlightList) {
    generalFlightList.stream()
            .filter(generalFlightModel -> generalFlightModel.getProvider().equals(ProviderEnum.THIRD_SERVICE.name())).findFirst().ifPresent(generalFlightModel -> {
              flightData.setThirdServicePrice(generalFlightModel.getAdultTotalPrice());
              flightData.setThirdServiceSeat(generalFlightModel.getRemainSeat());
            });
  }

  private void setSecondServiceInformation(FlightData flightData, List<GeneralFlightModel> generalFlightList) {
    generalFlightList.stream()
            .filter(generalFlightModel -> generalFlightModel.getProvider().equals(ProviderEnum.SECOND_SERVICE.name())).findFirst().ifPresent(generalFlightModel -> {
              flightData.setSecondServicePrice(generalFlightModel.getAdultTotalPrice());
              flightData.setSecondServiceSeat(generalFlightModel.getRemainSeat());
            });
  }

  private void setFirstServiceInformation(FlightData flightData, List<GeneralFlightModel> generalFlightList) {
    generalFlightList.stream()
            .filter(generalFlightModel -> generalFlightModel.getProvider().equals(ProviderEnum.FIRST_PROVIDER.name())).findFirst().ifPresent(generalFlightModel -> {
              flightData.setFirstServicePrice(generalFlightModel.getAdultTotalPrice());
              flightData.setFirstServiceSeat(generalFlightModel.getRemainSeat());
            });
  }

  Map<String, String> thirdServiceAirlineMap =
      Map.ofEntries(
          entry("ZV", "Z"),
          entry("I3", "A"),
          entry("FP0", "FP"),
          entry("_VR", "VA"),
          entry("IV", "CA"),
          entry("EP", "EP"),
          entry("HH", "TA"),
          entry("_007", "SA"),
          entry("IR", "II"),
          entry("B9", "IE"),
          entry("W5", "MA"),
          entry("_008", "SP"),
          entry("JI", "ME"));

}
