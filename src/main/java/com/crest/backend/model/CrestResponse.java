package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by Arun on 10/13/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CrestResponse {
    private String statusCode;
    private String busNumber;
    private String routeDescription;
    private String statusDescripton;
    private String predictedValue;

    public String getPredictedValue() {
        return predictedValue;
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
