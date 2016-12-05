package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sgangras on 12/4/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Caregiver {
    private String id;
    private String name;
    private String profileImage;
    private String address;
    private String contactNumber;
    private String age;
    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
