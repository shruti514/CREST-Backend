package com.crest.backend.com.crest.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;

import java.io.FileReader;


public class Predictor {


    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final String LINE_55_NORTH_ON_COUNT = "Bus55NorthCountOn_Random_Forest_tree.model";
    private final String LINE_60_NORTH_ON_COUNT = "Bus60NorthCountOn_Random_Forest_tree.model";
    private final String LINE_181_NORTH_ON_COUNT = "Bus181NorthCountOn_Random_Forest_tree.model";
    private final String LINE_55_NORTH_OFF_COUNT = "Bus55NorthCountOff_Random_Forest_tree.model";
    private final String LINE_60_NORTH_OFF_COUNT = "Bus60NorthCountOff_Random_Forest_tree.model";
    private final String LINE_181_NORTH_OFF_COUNT = "Bus181NorthCountOff_Random_Forest_tree.model";
    private final String arff_55_NORTH_ON_COUNT = "Bus55NorthCountOn_Random_Forest_tree.arff";
    private final String arff_60_NORTH_ON_COUNT = "Bus60NorthCountOn_Random_Forest_tree.arff";
    private final String arff_181_NORTH_ON_COUNT = "Bus181NorthCountOn_Random_Forest_tree.arff";
    private final String arff_55_NORTH_OFF_COUNT = "Bus55NorthCountOff_Random_Forest_tree.arff";
    private final String arff_60_NORTH_OFF_COUNT = "Bus60NorthCountOff_Random_Forest_tree.arff";
    private final String arff_181_NORTH_OFF_COUNT = "Bus181NorthCountOff_Random_Forest_tree.arff";
    private String arffFileLocation;
    private String modelRootPath;

    public Predictor(String modelRootPath, String arffFileLocation) {
        this.modelRootPath = modelRootPath;
        this.arffFileLocation = arffFileLocation;
    }

    public String predict(String busNumber) {

        String modelFile = getModelFileToLoad(busNumber);
        String arffFile = getArffFileToLoad(busNumber);
        String prediction = "";
        try {
            Classifier cls = (Classifier) weka.core.SerializationHelper.read(modelFile);
            Instances instances = new Instances(new FileReader(arffFile));
            instances.setClassIndex(3);
            //which instance to predict class value
            int size = instances.size();
            //perform your prediction
            Instance instance = instances.instance(size - 1);
            double value = cls.classifyInstance(instance);
            //get the prediction percentage or distribution
            double[] percentage = cls.distributionForInstance(instance);
            //get the name of the class value
            prediction = instances.classAttribute().value((int) value);

            logPrediction(prediction, instance);
            logDistribution(value, percentage);

        } catch (Exception ex) {
            log.error("Error occurred in predict()",ex.getMessage());
        }
        return prediction;
    }

    private void logPrediction(String prediction, Instance instance) {
        log.info("-----------------------------------------------");
        log.info("Instance => " + instance.toStringNoWeight());
        log.info("Prediction => " + prediction);
        log.info("-----------------------------------------------");
    }

    private void logDistribution(double value, double[] percentage) {
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

    private String getModelFileToLoad(String busNumber) {
        switch (busNumber) {
            case "55":
                return modelRootPath + LINE_55_NORTH_ON_COUNT;
            case "60":
                return modelRootPath + LINE_60_NORTH_ON_COUNT;
            case "181":
                return modelRootPath + LINE_181_NORTH_ON_COUNT;
            default:
                throw new IllegalArgumentException("No model found for bus");
        }

    }

}
