package com.github.alirezagolshan.crawler.api.dto;

import com.github.alirezagolshan.crawler.model.FlightType;
import com.github.alirezagolshan.crawler.model.TripType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class FlightRequestModel {

    private String sourceAirportCode;
    private String targetAirportCode;
    private Integer adultCount = 0;
    private Integer childCount = 0;
    private Integer infantCount = 0;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate leaveDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate returnDate;
    private TripType tripType = TripType.ONE_WAY;
    private FlightType flightType = FlightType.ECONOMY;

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public Integer getInfantCount() {
        return infantCount;
    }

    public void setInfantCount(Integer infantCount) {
        this.infantCount = infantCount;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public TripType getTripType() {
        return tripType;
    }

    public void setTripType(TripType tripType) {
        this.tripType = tripType;
    }

    public String getSourceAirportCode() {
        return sourceAirportCode;
    }

    public void setSourceAirportCode(String sourceAirportCode) {
        this.sourceAirportCode = sourceAirportCode;
    }

    public void setTargetAirportCode(String targetAirportCode) {
        this.targetAirportCode = targetAirportCode;
    }

    public String getTargetAirportCode() {
        return targetAirportCode;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public void setFlightType(FlightType flightType) {
        this.flightType = flightType;
    }
}
