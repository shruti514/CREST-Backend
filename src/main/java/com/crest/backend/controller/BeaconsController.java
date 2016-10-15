package com.crest.backend.controller;

import com.crest.backend.model.CrestResponse;
import com.crest.backend.service.BeaconService;
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
@RequestMapping("/stops")
public class BeaconsController {
    BeaconService beaconService = new BeaconService();
    CrestResponse crestResponse = new CrestResponse();
    @Autowired
    WekaService wekaService;

    @RequestMapping(value = "/program/test", method = RequestMethod.GET)
    public
    @ResponseBody
    String programABC() throws IOException {
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


}
