package com.crest.backend.service;


import com.crest.backend.com.crest.backend.constants.Service;
import com.crest.backend.com.crest.backend.constants.StopIdToStopName;
import com.crest.backend.com.crest.backend.constants.TimeBucket;
import com.crest.backend.com.crest.backend.util.DataWriter;
import com.crest.backend.com.crest.backend.util.Predictor;
import com.crest.backend.model.CrestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import static com.crest.backend.com.crest.backend.constants.TripMonth.toTripMonth;


@org.springframework.stereotype.Service
@PropertySource("classpath:/application.properties")
public class WekaService {

    @Value("${modelFile.location}")
    private String modelFileLocation;

    @Value("${arffFile.location}")
    private String arffLocation;

    public CrestResponse getWekaModel(String tripMonth, String service, String timeBucket, String tripRoute, String busNumber) {
        DataWriter dataWriter = new DataWriter(arffLocation);
        String stopName = StopIdToStopName.stopIdToStopName.get(tripRoute);
        dataWriter.writeDataToArffFile(toTripMonth(tripMonth), Service.toService(service), TimeBucket.toTimeBucket(timeBucket), stopName, busNumber);

        Predictor predictor = new Predictor(modelFileLocation, arffLocation);
        String prediction = predictor.predict(busNumber);
        CrestResponse crestResponse = new CrestResponse();

        if (!prediction.equals("")) {
            crestResponse.setStatusCode("200");
            crestResponse.setStatusDescripton("Ok");
            crestResponse.setPredictedValue("At this time of the day " + prediction + " passengers are estimated to board bus " + busNumber);
        } else {
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
        }

        return crestResponse;
    }
}
