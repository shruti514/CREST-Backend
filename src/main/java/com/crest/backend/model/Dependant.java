package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sgangras on 12/4/16.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Dependant {
    private String name;
    private String address;
    private String phoneNumber;
    private String profileImage;
    private String currentTripStatus;
    private String currentLocation;
    private String id;
    private String emailId;
    private String emergencyContactNumber;
    private String emergencyContactName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getCurrentTripStatus() {
        return currentTripStatus;
    }

    public void setCurrentTripStatus(String currentTripStatus) {
        this.currentTripStatus = currentTripStatus;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getId() {
        return id;
    }

    public String getEmailId() {
        return emailId;
    }
}
