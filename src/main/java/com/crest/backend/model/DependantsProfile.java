package com.crest.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by sgangras on 12/4/16.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DependantsProfile {
    private Dependant dependant;
    private Caregiver caregiver;

    public Dependant getDependant() {
        return dependant;
    }

    public void setDependant(Dependant dependant) {
        this.dependant = dependant;
    }

    public Caregiver getCaregiver() {
        return caregiver;
    }

    public void setCaregiver(Caregiver caregiver) {
        this.caregiver = caregiver;
    }
}
