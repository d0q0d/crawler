package com.github.alirezagolshan.crawler.service.model.thirdservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alirexa
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdServiceFlight {
    @JsonProperty("DepartureTimeString")
    private String departureTime;
    @JsonProperty("ArrivalTimeString")
    private String arrivalTime;
    @JsonProperty("FlightNumber")
    private String flightNumber;
    @JsonProperty("OperatingAirline")
    private String airlineCode;
    @JsonProperty("OperatingAirline")
    private String departureAirportName;
    @JsonProperty("SeatsRemaining")
    private Integer remainSeat;

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public void setDepartureAirportName(String departureAirportName) {
        this.departureAirportName = departureAirportName;
    }

    public Integer getRemainSeat() {
        return remainSeat;
    }

    public void setRemainSeat(Integer remainSeat) {
        this.remainSeat = remainSeat;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
}
