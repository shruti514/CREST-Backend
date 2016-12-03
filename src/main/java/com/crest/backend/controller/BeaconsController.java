package com.crest.backend.controller;

import com.crest.backend.model.CrestResponse;
import com.crest.backend.pushAPN.SendPushNotifications;
import com.crest.backend.service.BeaconService;
import com.crest.backend.service.UserService;
import com.crest.backend.service.WekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@Service
@PropertySource("classpath:/application.properties")
@RequestMapping("/cense")
public class BeaconsController {
    BeaconService beaconService = new BeaconService();
    CrestResponse crestResponse = new CrestResponse();
    @Autowired
    WekaService wekaService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/program/test", method = RequestMethod.GET)
    public
    @ResponseBody
    String programABC() throws IOException {
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendPushNotifications("userId","55","Started");
        return "testStringIsWorking";
    }

    @RequestMapping(value = "/station/{beaconId}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse getStationFromBeaconID(@PathVariable("beaconId") String beaconId) throws Exception {

        crestResponse = beaconService.getStationFromBeaconId(beaconId);
        return crestResponse;

    }

    @RequestMapping(value = "/station/{busStopId1}/{busStopId2}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse getStationFromBeaconID(@PathVariable("busStopId1") String busStopId1, @PathVariable("busStopId2") String busStopId2) throws Exception {

        crestResponse = beaconService.getStationFromBeaconId(busStopId1, busStopId2);
        return crestResponse;

    }

    @RequestMapping(value = "/crowdEstimator/{tripMonth}/{service}/{timeBucket}/{tripRoute}/{busNumber}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse getEstimatedCrowd(@PathVariable("tripMonth") String tripMonth, @PathVariable("service") String service,
                                    @PathVariable("timeBucket") String timeBucket, @PathVariable("tripRoute") String tripRoute, @PathVariable("busNumber") String busNumber) {
        crestResponse = wekaService.getWekaModel(tripMonth, service, timeBucket, tripRoute, busNumber);
        return crestResponse;
    }

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


    @RequestMapping(value = "/user/caregiver/register/{firstName}/{lastName}/{age}/{contactNumber}/{address}/{userName}/{password}", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse careGiverRegister(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
                                    @PathVariable("age") String age, @PathVariable("address") String address,
                                    @PathVariable("contactNumber") String contactNumber,
                                    @PathVariable("userName") String userName, @PathVariable("password") String password) throws Exception {

        crestResponse = userService.careGiverRegister(firstName, lastName, age, address, contactNumber, userName, password);

        return crestResponse;


    }

    @RequestMapping(value = "/user/register/{firstName}/{lastName}/{contactNumber}/{age}/{address}/{emergencyContact}" +
            "/{careGiverEmail}/{additionalInfo}/{userName}/{password}", method = RequestMethod.POST)
    public
    @ResponseBody
    CrestResponse userRegister(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
                               @PathVariable("contactNumber") String contactNumber, @PathVariable("age") String age,
                               @PathVariable("address") String address, @PathVariable("emergencyContact") String emergencyContact,
                               @PathVariable("careGiverEmail") String careGiverEmail,
                               @PathVariable("additionalInfo") String additionalInfo, @PathVariable("userName") String userName,
                               @PathVariable("password") String password) throws Exception {

        crestResponse = userService.userRegister(firstName, lastName, contactNumber, age, address, emergencyContact, careGiverEmail, additionalInfo, userName, password);

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
        return crestResponse;

    }




}
