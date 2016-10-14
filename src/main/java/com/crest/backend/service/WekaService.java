package com.crest.backend.service;

/**
 * Created by Arun on 10/13/16.
 *
 */

import com.crest.backend.com.crest.backend.constants.Service;
import com.crest.backend.com.crest.backend.constants.TimeBucket;
import com.crest.backend.com.crest.backend.constants.TripMonth;
import com.crest.backend.com.crest.backend.util.DataWriter;
import com.crest.backend.com.crest.backend.util.Predictor;
import com.crest.backend.model.CrestResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;


@org.springframework.stereotype.Service
@PropertySource("classpath:/application.properties")
public class WekaService {

    @Value("${modelFile.location}")
    private String modelFileLocation;

    @Value("${arffFile.location}")
    private String arffLocation;

    public CrestResponse getWekaModel(String tripMonth, String service, String timeBucket, String tripRoute, String busNumber) {
        DataWriter dataWriter = new DataWriter(arffLocation);
        dataWriter.writeDataToArffFile(TripMonth.valueOf(tripMonth), Service.valueOf(service), TimeBucket.valueOf(timeBucket), tripRoute,busNumber);

        Predictor predictor = new Predictor(modelFileLocation,arffLocation);
        String prediction = predictor.predict(busNumber);
        CrestResponse crestResponse=new CrestResponse();

        if(!prediction.equals("")) {
            crestResponse.setStatusCode("200");
            crestResponse.setStatusDescripton("Ok");
            crestResponse.setPredictedValue(prediction);
        }else{
            crestResponse.setStatusCode("500");
            crestResponse.setStatusDescripton("Internal Server Error");
        }

        return crestResponse;
    }
}
