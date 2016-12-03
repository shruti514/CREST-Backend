package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrestResponse {
    private String statusCode;
    private String busNumber;
    private String routeDescription;
    private String statusDescripton;
    private String predictedValue;
    private String userId;
    private String tripId;

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getPredictedValue() {
        return predictedValue;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPredictedValue(String predictedValue) {
        this.predictedValue = predictedValue;
    }

    public String getStatusDescripton() {
        return statusDescripton;
    }

    public void setStatusDescripton(String statusDescripton) {
        this.statusDescripton = statusDescripton;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }

    public String getRouteDescription() {
        return routeDescription;
    }

    public void setRouteDescription(String routeDescription) {
        this.routeDescription = routeDescription;
    }
}
