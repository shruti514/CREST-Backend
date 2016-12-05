package com.crest.backend.controller;


import com.crest.backend.model.CrestResponse;
import com.crest.backend.model.Dependant;
import com.crest.backend.model.DependantsProfile;
import com.crest.backend.pushAPN.SendPushNotifications;
import com.crest.backend.service.BeaconService;
import com.crest.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Created by sgangras on 12/4/16.
 */
@Controller
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


    @RequestMapping(value = "/user/caregiver/register/", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse careGiverRegister(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                    @RequestParam("age") String age, @RequestParam("address") String address,
                                    @RequestParam("contactNumber") String contactNumber,
                                    @RequestParam("email") String userName, @RequestParam("password") String password) throws Exception {

        crestResponse = userService.careGiverRegister(firstName, lastName, age, address, contactNumber, userName, password);

        return crestResponse;


    }

    @RequestMapping(value = "/user/dependant/register/", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse userRegister(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                               @RequestParam("contactNumber") String contactNumber, @RequestParam("age") String age,
                               @RequestParam("address") String address, @RequestParam("emergencyContact") String emergencyContact,
                               @RequestParam("careGiverId") String careGiverId,
                               @RequestParam("additionalInfo") String additionalInfo, @RequestParam("email") String email,
                               @RequestParam("password") String password) throws Exception {

        crestResponse = userService.userRegister(firstName, lastName, contactNumber, age, address, emergencyContact, careGiverId, additionalInfo, email, password);

        return crestResponse;
    }


    @RequestMapping(value = "/user/schedule/{riderId}/{schedulerId}/{tripStartTime}/{tripDate}/{source}/{destination}", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse userScheduleTrip(@PathVariable("riderId") String riderId, @PathVariable("schedulerId") String schedulerId, @PathVariable("tripStartTime") String tripStartTime, @PathVariable("tripDate") String tripDate,
                                   @PathVariable("source") String source, @PathVariable("destination") String destination) throws Exception {

        crestResponse = userService.scheduleTrip(riderId, schedulerId, tripStartTime, tripDate, source, destination);
        return crestResponse;
    }

    @RequestMapping(value = "/user/{userId}/{busNumber}/{tripStatus}", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse getUserLocation(@PathVariable("userId") String userId, @PathVariable("busNumber") String busNumber, @PathVariable("tripStatus") String tripStatus) throws Exception {

        crestResponse = beaconService.getUserLocation(userId, busNumber, tripStatus);
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendPushNotifications(userId, busNumber, tripStatus);
        return crestResponse;

    }


    @RequestMapping(value = "/caregiver/{caregiverId}/dependents", method = RequestMethod.GET)
    @ResponseBody
    public List<Dependant> getAllPatients(@PathVariable("caregiverId") String caregiverId) throws Exception {

        List<Dependant> allDependents = userService.getAllDependents(caregiverId);
        return allDependents;
    }

    @RequestMapping(value = "/dependent/{userId}/profile", method = RequestMethod.GET)
    @ResponseBody
    public DependantsProfile getDependantsProfile(@PathVariable("userId") String userId) throws Exception {

        DependantsProfile dependantsProfile = userService.getDependantsProfile(userId);
        return dependantsProfile;
    }


}
