package com.crest.backend.service;

/**
 * Created by Arun on 10/13/16.
 */

import org.springframework.stereotype.Service;
import weka.classifiers.Classifier;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;
import weka.core.Instances;

import java.io.*;

@Service
public class WekaService {
    String rootPath="/CREST/CREST-Backend/src/main/resources/";

    public void getWekaModel(String busNumber) {
        try {
            if(busNumber.equals("55")) {
                Classifier cls = (Classifier) weka.core.SerializationHelper.read(rootPath+"Bus55NorthOnCount.model");
                //Instances originalTrain= new Instances();
                Instances originalTrain=null;
                int s1=0;
                double value=cls.classifyInstance(originalTrain.instance(s1));
                String prediction=originalTrain.classAttribute().value((int)value);

                System.out.println("The predicted value of instance "+
                        Integer.toString(s1)+
                        ": "+prediction);


            }else if(busNumber.equals("60")){
                //RandomForest rf = (RandomForest) (new ObjectInputStream(rootPath+"Bus55NorthOnCount.model")).readObject();
                //rf.classifyInstance();

            }else if(busNumber.equals("181")){

            }
        }catch (Exception ie){
            ie.printStackTrace();
        }
    }
}
