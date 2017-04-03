package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sgangras on 4/2/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BeaconDetails {

    private String beaconId;
    private int major;
    private int minor;
    private String deploymentType;
    private String deploymentDetails;


    public String getBeaconId() {
        return beaconId;
    }

    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public int getMajor() {
        return major;
    }

    public void setMajor(int major) {
        this.major = major;
    }

    public int getMinor() {
        return minor;
    }

    public void setMinor(int minor) {
        this.minor = minor;
    }

    public String getDeploymentType() {
        return deploymentType;
    }

    public void setDeploymentType(String deploymentType) {
        this.deploymentType = deploymentType;
    }

    public String getDeploymentDetails() {
        return deploymentDetails;
    }

    public void setDeploymentDetails(String deploymentDetails) {
        this.deploymentDetails = deploymentDetails;
    }
}
