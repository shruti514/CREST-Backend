package com.crest.backend.controller;


import com.crest.backend.model.CrestResponse;
import com.crest.backend.model.Dependant;
import com.crest.backend.model.DependantsProfile;
import com.crest.backend.model.Trip;
import com.crest.backend.pushAPN.SendPushNotifications;
import com.crest.backend.service.BeaconService;
import com.crest.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by sgangras on 12/4/16.
 */
@RestController
@Service
@PropertySource("classpath:/application.properties")
@RequestMapping("/cense")
public class UserController {
    private BeaconService beaconService = new BeaconService();
    private CrestResponse crestResponse = new CrestResponse();

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user/login/{userName}/{password}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse userLogin(@PathVariable("userName") String userName, @PathVariable("password") String password) throws Exception {

        crestResponse = userService.userLogin(userName, password);
        if (crestResponse.getUserId() != null) {
            return crestResponse;
        } else {
            crestResponse.setStatusDescripton("Invalid user");
            return crestResponse;
        }
    }

    @RequestMapping(value = "/user/login/{birthdate}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse userLogin(@PathVariable("birthdate") String birthdate) throws Exception {

        crestResponse = userService.dependantLogin(birthdate);
        if (crestResponse.getUserId() != null) {
            return crestResponse;
        } else {
            crestResponse.setStatusDescripton("Invalid user");
            return crestResponse;
        }
    }


    @RequestMapping(value = "/user/caregiver/register/", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    CrestResponse careGiverRegister(@RequestBody Map<String, String> request) throws Exception {
        crestResponse = userService.careGiverRegister(request.get("firstName"), request.get("lastName"),
                request.get("age"), request.get("address"), request.get("contactNumber"),
                request.get("email"),request.get("password"));

        return crestResponse;
    }

    

    @RequestMapping(value = "/user/dependant/register/", method = RequestMethod.POST,consumes = "application/json", produces = "application/json")
    public
    @ResponseBody
    CrestResponse userRegister(@RequestBody Map<String,String> requestBody) throws Exception {

        crestResponse = userService.userRegister(requestBody.get("firstName"), requestBody.get("lastName"), requestBody.get("contactNumber"), requestBody.get("age"),
                requestBody.get("address"), requestBody.get("emergencyContact"), requestBody.get("careGiverId"), requestBody.get("additionalInfo"), requestBody.get("email"), requestBody.get("dateOfBirth"));

        return crestResponse;
    }


    @RequestMapping(value = "/user/schedule/", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
    public
    @ResponseBody
    CrestResponse userScheduleTrip(@RequestBody Map<String,String> requestBody) throws Exception {
        crestResponse = userService.scheduleTrip(requestBody.get("riderId"), requestBody.get("schedulerId"), requestBody.get("tripStartTime"),
                requestBody.get("tripDate"), requestBody.get("source"), requestBody.get("destination"));
        return crestResponse;
    }

    @RequestMapping(value = "/user/{userId}/schedule/", method = RequestMethod.GET,produces = "application/json")
    public
    @ResponseBody
    List<Trip> getUserSchedule(@PathVariable String userId ) throws Exception {
        List<Trip> trips = userService.getUserSchedule(userId);
        return trips;
    }

    @RequestMapping(value = "/caregiver/{userId}/trips/", method = RequestMethod.GET,produces = "application/json")
    public
    @ResponseBody
    List<Trip> getTripsScheduledByCaregiver(@PathVariable String userId ) throws Exception {
        List<Trip> trips = userService.getTripsScheduledByCaregiver(userId);
        return trips;
    }


    @RequestMapping(value = "/user/updateUser/{userId}/{busNumber}/{tripStatus}", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse getUserLocation(@PathVariable("userId") String userId, @PathVariable("busNumber") String busNumber, @PathVariable("tripStatus") String tripStatus) throws Exception {

        crestResponse = beaconService.getUserLocation(userId, busNumber, tripStatus);
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendPushNotifications(userId, busNumber, tripStatus);
        return crestResponse;

    }


    @RequestMapping(value = "/caregiver/dependents/{caregiverId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Dependant> getAllPatients(@PathVariable("caregiverId") String caregiverId) throws Exception {

        List<Dependant> allDependents = userService.getAllDependents(caregiverId);
     
        return allDependents;
    }

    @RequestMapping(value = "/dependent/profile/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public DependantsProfile getDependantsProfile(@PathVariable("userId") String userId) throws Exception {

        DependantsProfile dependantsProfile = userService.getDependantsProfile(userId);
        return dependantsProfile;
    }


}
