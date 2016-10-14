package com.crest.backend.com.crest.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

import java.io.FileReader;

/**
 * Created by Arun on 10/13/16.
 */
public class Predictor {

    protected final Logger log = LoggerFactory.getLogger(getClass());
        public String predict(String busNumber) {
            String rootPath = "/Users/Arun/Desktop/VTA Ridership Data/Models/";
            String prediction = "";
            try {

                Classifier cls=(Classifier) weka.core.SerializationHelper.read(rootPath + "Bus55NorthOnCount.model");
                Instances instances= new Instances(new FileReader(rootPath + "line55_North_tmp.arff"));

                if(busNumber.equals("60")){
                    //Route Number 60s arff and model
                    cls = (Classifier) weka.core.SerializationHelper.read(rootPath + "Bus55NorthOnCount.model");

                    //predict instance class values
                    instances = new Instances(new FileReader(rootPath + "line55_North_tmp.arff"));

                }else if(busNumber.equals("181")){
                    //Route Number 181s arff and model
                    cls = (Classifier) weka.core.SerializationHelper.read(rootPath + "Bus55NorthOnCount.model");

                    //predict instance class values

                    instances = new Instances(new FileReader(rootPath + "line55_North_tmp.arff"));

                }

                instances.setClassIndex(3);

                //which instance to predict class value\
                int size = instances.numInstances();
                // int size = instances.size(); Method size is not available for instances

                //perform your prediction
                Instance instance = instances.instance(size - 1);
                double value = cls.classifyInstance(instance);

                //get the prediction percentage or distribution
                double[] percentage = cls.distributionForInstance(instance);

                //get the name of the class value
                prediction = instances.classAttribute().value((int) value);

                log.info("-----------------------------------------------");
                log.info("Instance => " + instance.toString());
                //log.info("Instance => " +instance.toStringNoWeight()); --- Protected Method and unable to access
                log.info("Prediction => " + prediction);
                log.info("-----------------------------------------------");


                //Format the distribution
                String distribution = "";
                for (int i = 0; i < percentage.length; i = i + 1) {
                    if (i == value) {
                        distribution = distribution + "*" + Double.toString(percentage[i]) + ",";
                    } else {
                        distribution = distribution + Double.toString(percentage[i]) + ",";
                    }
                }
                distribution = distribution.substring(0, distribution.length() - 1);

                log.info("Distribution:" + distribution);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return prediction;
        }

}
