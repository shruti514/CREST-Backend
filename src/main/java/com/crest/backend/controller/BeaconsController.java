package com.crest.backend.controller;

import com.crest.backend.model.BeaconDetails;
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

    @RequestMapping(value = "/user/{userId}/sendpushnotification/", method = RequestMethod.GET)
    public
    @ResponseBody
    String sendPushNotification(@PathVariable String userId) throws IOException {
        SendPushNotifications sendPushNotifications = new SendPushNotifications();
        sendPushNotifications.sendFallPushNotifications(userId);
        return "Notification Sent";
    }


    @RequestMapping(value = "/station/{beaconId}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse getStationFromBeaconID(@PathVariable("beaconId") String beaconId) throws Exception {

        crestResponse = beaconService.getStationFromBeaconId(beaconId);
        return crestResponse;

    }

    @RequestMapping(value = "/beacon/{beaconId}", method = RequestMethod.GET)
    public
    @ResponseBody
    BeaconDetails getBeaconDetails(@PathVariable("beaconId") String beaconId) throws Exception {

        BeaconDetails beaconDetails = beaconService.getBeaconDetails(beaconId);
        return beaconDetails;
    }

    @RequestMapping(value = "/navigate/{srcBeaconId}/{destBeaconId}", method = RequestMethod.GET)
    public
    @ResponseBody
    CrestResponse getNavigatinBetwnBeacons(@PathVariable("srcBeaconId") String srcBeaconId, @PathVariable("destBeaconId") String destBeaconId) throws Exception {

        crestResponse = beaconService.getNavigatinBetwnBeacons(srcBeaconId, destBeaconId);
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

}
