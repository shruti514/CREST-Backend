package com.crest.backend.com.crest.backend.util;

import com.crest.backend.com.crest.backend.constants.Service;
import com.crest.backend.com.crest.backend.constants.TimeBucket;
import com.crest.backend.com.crest.backend.constants.TripMonth;

import java.io.File;
import java.io.FileWriter;

public class DataWriter {
    private final String arff_55_NORTH_ON_COUNT = "Bus55NorthCountOn_Random_Forest_tree.arff";
    private final String arff_60_NORTH_ON_COUNT = "Bus60NorthCountOn_Random_Forest_tree.arff";
    private final String arff_181_NORTH_ON_COUNT = "Bus181NorthCountOn_Random_Forest_tree.arff";
    private final String arff_55_NORTH_OFF_COUNT = "Bus55NorthCountOff_Random_Forest_tree.arff";
    private final String arff_60_NORTH_OFF_COUNT = "Bus60NorthCountOff_Random_Forest_tree.arff";
    private final String arff_181_NORTH_OFF_COUNT = "Bus181NorthCountOff_Random_Forest_tree.arff";
    private String arffFileLocation;

    public DataWriter(String arffFileLocation) {
        this.arffFileLocation = arffFileLocation;
    }

    public boolean writeDataToArffFile(TripMonth tripMonth, Service service, TimeBucket timeBucket, String stopName, String busNumber) {

        try {
            String timeBucketValue = timeBucket == TimeBucket.EARLY_MORNING ? "\'" + TimeBucket.EARLY_MORNING.getDisplayValue() + "\'" : timeBucket.getDisplayValue();
            String data = "\n" + tripMonth.getMonthSequence() + "," + service.getValue() + "," +
                    timeBucketValue + "," + "0" + "," + "\'" + stopName + "\'";

            File file = new File(getArffFileToLoad(busNumber));

            //if file doesnt exists, then create it
            if (!file.exists()) {
                System.out.println("Creating a file..");
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();

            System.out.println("Done");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    private String getArffFileToLoad(String busNumber) {
        switch (busNumber) {
            case "55":
                return arffFileLocation + arff_55_NORTH_ON_COUNT;
            case "60":
                return arffFileLocation + arff_60_NORTH_ON_COUNT;
            case "181":
                return arffFileLocation + arff_181_NORTH_ON_COUNT;
            default:
                throw new IllegalArgumentException("No arff found for bus");
        }
    }


}

