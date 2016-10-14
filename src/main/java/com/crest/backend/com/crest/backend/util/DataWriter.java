package com.crest.backend.com.crest.backend.util;

import com.crest.backend.com.crest.backend.constants.Service;
import com.crest.backend.com.crest.backend.constants.TimeBucket;
import com.crest.backend.com.crest.backend.constants.TripMonth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Arun on 10/13/16.
 */
public class DataWriter {
    String rootPath="/Users/Arun/Desktop/VTA Ridership Data/Models";
    public boolean writeDataToArffFile(TripMonth tripMonth, Service service, TimeBucket timeBucket, String stopName){

        try{
            String timeBucketValue = timeBucket == TimeBucket.EARLY_MORNING? "\'"+TimeBucket.EARLY_MORNING.getDisplayValue()+"\'":timeBucket.getDisplayValue();
            String data = "\n"+tripMonth.getMonthSequence()+","+service.getValue()+","+
                    timeBucketValue+","+"0"+","+"\'"+stopName+"\'";

            File file = new File(rootPath+"/line55_North_tmp.arff");

            //if file doesnt exists, then create it
            if(!file.exists()){
                System.out.println("Creating a file..");
                file.createNewFile();
            }

            //true = append file
            FileWriter fileWriter = new FileWriter(file,true);
            fileWriter.write(data);
            fileWriter.flush();
            fileWriter.close();

            System.out.println("Done");

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }



    public void write() {
        File file = new File(rootPath+"/Hello1.txt");

        // creates the file
        try {
            file.createNewFile();


            // creates a FileWriter Object
            FileWriter writer = new FileWriter(file,true);

            // Writes the content to the file
            writer.write("This\n is\n an\n example\n");
            writer.flush();
            writer.close();



        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

