package com.crest.backend.service;

/**
 * Created by Arun on 10/13/16.
 */

import com.crest.backend.com.crest.backend.constants.Service;
import com.crest.backend.com.crest.backend.constants.TimeBucket;
import com.crest.backend.com.crest.backend.constants.TripMonth;
import com.crest.backend.com.crest.backend.util.DataWriter;
import com.crest.backend.com.crest.backend.util.Predictor;
import com.crest.backend.model.CrestResponse;


@org.springframework.stereotype.Service
public class WekaService {
    String prediction;
    String rootPath="/Users/Arun/Desktop/VTA Ridership Data/Models/";
    CrestResponse crestResponse=new CrestResponse();

    public CrestResponse getWekaModel(String tripMonth, String service, String timeBucket, String tripRoute, String busNumber) {
        tripRoute = "DE ANZA & STEVENS CREEK";
        DataWriter dataWriter = new DataWriter();
        dataWriter.writeDataToArffFile(TripMonth.valueOf(tripMonth), Service.valueOf(service), TimeBucket.valueOf(timeBucket), tripRoute);
        dataWriter.write();

        Predictor predictor = new Predictor();
        prediction=predictor.predict(busNumber);
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
