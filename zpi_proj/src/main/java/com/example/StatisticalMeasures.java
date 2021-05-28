package com.example;

import java.util.ArrayList;
import java.util.Collections;

public class StatisticalMeasures {
    static double getMedian(ArrayList<Double> list){
        if(list==null || list.isEmpty())
            return -1;
        Collections.sort(list);
        double median;
        if (list.size() % 2 == 0)
            median = (list.get(list.size()/2) + (list.get(list.size()/2 - 1)))/2;
        else
            median = list.get(list.size()/2);
        return median;
    }

    static double getMode(ArrayList<Double> list){
        if(list==null || list.isEmpty())
            return -1;
        double maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < list.size(); ++i) {
            int count = 0;
            for (int j = 0; j< list.size(); j++) {
                if (list.get(j).equals(list.get(i)) && i != j)
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = list.get(i);
            }
        }
        return maxValue;
    }

    static double getMean(ArrayList<Double> list){
        if(list==null || list.isEmpty())
            return -1;
        double sum = 0.0;

        for(double num : list) {
            sum += num;
        }

        return sum/list.size();
    }

    static double getStandardDeviation(ArrayList<Double> list){
        if(list==null || list.isEmpty())
            return -1;
        double standardDeviation = 0.0;
        double mean = getMean(list);

        for(double num: list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/list.size());
    }

    static double getCoefficientOfVariation(ArrayList<Double> list){
        if(list==null || list.isEmpty())
            return -1;
        return (getStandardDeviation(list) / getMean(list));
    }
}
