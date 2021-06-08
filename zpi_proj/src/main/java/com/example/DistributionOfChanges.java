package com.example;

import java.util.ArrayList;
import java.util.List;

public class DistributionOfChanges {
    private List<Double> distribution;

    DistributionOfChanges(List<Double> list){
        if (list == null || list.size() < 2)
            throw new IncorrectListException();
        distribution = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            double current_diff = ((list.get(i) / list.get(i - 1)) - 1) * 100;
            distribution.add(current_diff);
        }
    }

    public List<Double> getDistribution() {
        return this.distribution;
    }
    
}
