package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class DistributionOfChanges {
    private List<Double> distribution;
    // private class IncorrectTimePeroidException extends RuntimeException {
    //     IncorrectTimePeroidException() {
    //         super();
    //     }
    // }

    DistributionOfChanges(List<Double> currency1,List<Double> currency2) {
        if (currency1 == null || currency2 == null ||  currency1.size() != currency2.size() || currency1.size() < 2)
            throw new IncorrectListException();
        // if (timePeroid <= 0)
        //     throw new IncorrectTimePeroidException();
        System.out.println(currency1);
        System.out.println(currency2);
        // List<Double> diffrences = new ArrayList<>(currency1.size());
        // for(int i = 0 ;i<currency1.size();i++){
        //     diffrences.add(currency1.get(i) - currency2.get(i));
        // }
    }

    public List<Double> getDistribution() {
        return this.distribution;
    }

}
