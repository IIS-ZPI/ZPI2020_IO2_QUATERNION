package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Distributionvalue{
    private double min;
    private double max;
    private int counter;

    Distributionvalue(double min,double max,int counter){
        this.min = min;
        this.max = max;
        this.counter = counter;
    }
    @Override
    public String toString() {
        return "(" + min + ' ' + max + " ) value: " + counter;  
    }
}

public class DistributionOfChanges {
    private static final int INTERVAL_AMOUNT = 10;
    public static final boolean readyToBeTested = false;// set to true to activate tests
    private List<Distributionvalue> distribution;

    DistributionOfChanges(List<Double> currency1, List<Double> currency2) {
        if (currency1 == null || currency2 == null || currency1.size() != currency2.size() || currency1.size() < 2)
            throw new IncorrectListException();

        List<Double> dividedList= new ArrayList<>(currency1.size());
        for(int i = 0 ;i<currency1.size();i++){
            dividedList.add(currency1.get(i)/currency2.get(i));
        }
        
        List<Double> diffrenceList = new ArrayList<>(currency1.size());
        diffrenceList.add(0.0);
        for(int i = 1 ;i<dividedList.size();i++){
            diffrenceList.add(dividedList.get(i)-dividedList.get(i-1));
        }

        double max = Collections.max(diffrenceList);
        double min = Collections.min(diffrenceList);
        double[] intervals = linspace(min,max);
        int[] counters = {0};

        // start=1 range (index-1,index> compare with min and max value
        for(int i = 1;i<INTERVAL_AMOUNT;i++){
            for(int j = 0;j<diffrenceList.size();j++){
                if(diffrenceList.get(j) >= intervals[i-1] && diffrenceList.get(j) < intervals[i]){
                    counters[i-1]++;
                }
            }
        }
        distribution = new ArrayList<>(INTERVAL_AMOUNT-1);
        for(int i = 1;i<INTERVAL_AMOUNT;i++){
            distribution.add(new Distributionvalue(intervals[i-1],intervals[i],counters[i-1]));
        }
        //TODO IS THAT ALL?
    }

    private double[] linspace(double min, double max) {  
        double[] d = new double[INTERVAL_AMOUNT];  
        for (int i = 0; i < INTERVAL_AMOUNT; i++){  
            d[i] = min + i * (max - min) / (INTERVAL_AMOUNT - 1);  
        }  
        return d;  
    }  


    @Override
    public String toString() {
        return distribution.toString();
    }

    public List<Double> getDistribution() {
        return this.distribution;
    }

}
