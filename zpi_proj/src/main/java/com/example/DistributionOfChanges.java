package com.example;

import java.util.ArrayList;
import java.util.List;

public class DistributionOfChanges {
    public static final boolean readyToBeTested = false;// set to true to activate tests
    private List<Double> distribution;

    DistributionOfChanges(List<Double> currency1, List<Double> currency2) {
        if (currency1 == null || currency2 == null || currency1.size() != currency2.size() || currency1.size() < 2)
            throw new IncorrectListException();
        List<Double> dividedList= new ArrayList<>(currency1.size());
        for(int i = 0 ;i<currency1.size();i++){
            dividedList.add(currency1.get(i)/currency2.get(i));
        }
        distribution = new ArrayList<>(currency1.size());
        distribution.add(0.0);
        for(int i = 1 ;i<dividedList.size();i++){
            distribution.add(dividedList.get(i)-dividedList.get(i-1));
        }
    }
    @Override
    public String toString() {
        return distribution.toString();
    }

    public List<Double> getDistribution() {
        return this.distribution;
    }

}
