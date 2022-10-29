package com.github.alirezagolshan.crawler.service;

import com.github.alirezagolshan.crawler.service.mapper.CrawlerServiceMapper;
import com.github.alirezagolshan.crawler.api.dto.FlightRequestModel;
import com.github.alirezagolshan.crawler.model.FlightData;
import com.github.alirezagolshan.crawler.model.FlightOutputModel;
import com.github.alirezagolshan.crawler.model.GeneralFlightModel;
import com.github.alirezagolshan.crawler.model.ProviderEnum;
import com.github.alirezagolshan.crawler.repository.FlightDataRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FlightService {

  private final List<Provider> providers;
  private final CrawlerServiceMapper mapper;
  private final FlightDataRepository repository;

  public FlightService(List<Provider> providers, FlightDataRepository repository) {
    this.providers = providers;
    this.repository = repository;
    this.mapper = CrawlerServiceMapper.INSTANCE;
  }

  public FlightOutputModel getFreshFlightData(FlightRequestModel requestModel) {
    var flightDataMap = getFlightDataFromProviders(requestModel);
    return new FlightOutputModel(flightDataMap, LocalDateTime.now());
  }

  public List<FlightData> getFlightData(FlightRequestModel requestModel) {
   return getFlightDataFromDataSource(requestModel);
  }

  public List<FlightData> processFlightData(FlightRequestModel requestModel, Map<ProviderEnum, List<GeneralFlightModel>> flightDataMap) {
    var generalFlightList = getFlightListWithProviders(flightDataMap);
    List<GeneralFlightModel> normalizedGeneralFlightList =
        generalFlightList.stream()
            .map(this::normalizeFlightNumbersBetweenProviders)
            .collect(Collectors.toList());
    return generateFlightList(normalizedGeneralFlightList, requestModel);
  }

  public void saveAllFlightData(List<FlightData> flightList) {
    repository.saveAll(flightList);
  }

  private List<FlightData> generateFlightList(List<GeneralFlightModel> normalizedGeneralFlightList, FlightRequestModel requestModel) {
    var flightNumberGeneralFlightModelMap = new HashMap<String, List<GeneralFlightModel>>();
    putFlightNumbersAsKey(normalizedGeneralFlightList, flightNumberGeneralFlightModelMap);
    flightNumberGeneralFlightModelMap
        .keySet()
        .forEach(
            flightNumber -> {
              var flightListBasedOnFlightNumber = filterFlightListBasedOnFlightNumber(normalizedGeneralFlightList, flightNumber);
              removeDuplicateFlights(flightNumberGeneralFlightModelMap, flightNumber, flightListBasedOnFlightNumber);
            });
    return generateFinalFlightData(requestModel, flightNumberGeneralFlightModelMap);
  }

  private List<FlightData> generateFinalFlightData(FlightRequestModel requestModel, HashMap<String, List<GeneralFlightModel>> flightNumberGeneralFlightModelMap) {
    var flightDataList = new ArrayList<FlightData>();
    flightNumberGeneralFlightModelMap.forEach((flightNumber, flightList) -> {
      var flightData = new FlightData();
      mapper.setValuesBasedOnRequestModel(flightData, requestModel, flightNumber, flightList);
      flightDataList.add(flightData);
    });
    return flightDataList;
  }

  private List<GeneralFlightModel> filterFlightListBasedOnFlightNumber(List<GeneralFlightModel> normalizedGeneralFlightList, String flightNumber) {
    return normalizedGeneralFlightList.stream()
        .filter(
            normalizedGeneralFlightModel ->
                normalizedGeneralFlightModel.getFlightNumber().equals(flightNumber))
        .collect(Collectors.toList());
  }

  private void putFlightNumbersAsKey(List<GeneralFlightModel> normalizedGeneralFlightList, HashMap<String, List<GeneralFlightModel>> flightNumberGeneralFlightModelMap) {
    normalizedGeneralFlightList.forEach(
        generalFlightModel ->
            flightNumberGeneralFlightModelMap.put(generalFlightModel.getFlightNumber(), null));
  }

  private void removeDuplicateFlights(HashMap<String, List<GeneralFlightModel>> flightNumberGeneralFlightModelMap, String flightNumber, List<GeneralFlightModel> flightListBasedOnFlightNumber) {
    var uniqueFlightListBasedOnFlightNumberMap = new HashMap<String, GeneralFlightModel>();
    putProviderAsKey(flightListBasedOnFlightNumber, uniqueFlightListBasedOnFlightNumberMap);
    var uniqueFlightListBasedOnFlightNumberList =
        new ArrayList<>(uniqueFlightListBasedOnFlightNumberMap.values());
    flightNumberGeneralFlightModelMap.put(flightNumber, uniqueFlightListBasedOnFlightNumberList);
  }

  private void putProviderAsKey(List<GeneralFlightModel> flightListBasedOnFlightNumber, HashMap<String, GeneralFlightModel> uniqueFlightListBasedOnFlightNumberMap) {
    flightListBasedOnFlightNumber.forEach(
        generalFlightModel ->
            uniqueFlightListBasedOnFlightNumberMap.put(
                generalFlightModel.getProvider(), generalFlightModel));
  }

  private GeneralFlightModel normalizeFlightNumbersBetweenProviders(GeneralFlightModel generalFlightModel) {
    var flightNumber = generalFlightModel.getFlightNumber();
    removeNonNumericCharactersFromFlightNumber(flightNumber);
    var finalFlightNumber = removeRedundantNumbersFromFlightNumber(flightNumber);
    generalFlightModel.setFlightNumber(finalFlightNumber);
    return generalFlightModel;
  }

  private String removeRedundantNumbersFromFlightNumber(String flightNumber) {
    if (flightNumber.length() >= 5) return flightNumber.substring(flightNumber.length() - 4);
    return flightNumber;
  }

  private void removeNonNumericCharactersFromFlightNumber(String flightNumber) {
    flightNumber.replaceAll("[^\\d]", "");
  }

  private Map<ProviderEnum, List<GeneralFlightModel>> getFlightDataFromProviders(FlightRequestModel requestModel) {
    var flightDataMap = new EnumMap<ProviderEnum, List<GeneralFlightModel>>(ProviderEnum.class);
    providers.forEach(
        provider -> {
          var flightData = provider.getFlightData(requestModel);
          flightDataMap.put(flightData.getProviders(), flightData.getFlightList());
        });
    return flightDataMap;
  }

  private List<FlightData> getFlightDataFromDataSource(FlightRequestModel requestModel) {
    var flightDataOptional = repository.findFirstByFromAndToAndDepartureDateOrderByCreatedDateDesc(requestModel.getSourceAirportCode(), requestModel.getTargetAirportCode(), requestModel.getLeaveDate());
    return flightDataOptional.map(flightData -> repository.findAllByUniqueTag(flightData.getUniqueTag())).orElse(null);
  }

  private List<GeneralFlightModel> getFlightListWithProviders(Map<ProviderEnum, List<GeneralFlightModel>> providerFlightsMap) {
    var flightDataList = new ArrayList<GeneralFlightModel>();
    providerFlightsMap.forEach(
        (provider, flightData) -> removeDuplicateFromResults(flightDataList, flightData));
    return flightDataList;
  }

  private void removeDuplicateFromResults(ArrayList<GeneralFlightModel> flightDataList, List<GeneralFlightModel> flightData) {
    var finalList =
        new ArrayList<>(
            flightData.stream()
                .collect(
                    Collectors.toCollection(
                        () ->
                            new TreeSet<>(
                                Comparator.comparing(GeneralFlightModel::getFlightNumber)))));
    flightDataList.addAll(finalList);
  }
}
