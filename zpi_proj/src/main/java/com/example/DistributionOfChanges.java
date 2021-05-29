package com.example;

import java.util.ArrayList;

public class DistributionOfChanges {
    static ArrayList<Double> getDistributionOfChanges(ArrayList<Double> data){
        if(data==null || data.isEmpty())
            return null;
        ArrayList<Double> diff = new ArrayList<>();
        for(int i = 1; i < data.size(); i++){
            double current_diff = 100 - (data.get(i) * 100 / data.get(i-1));
            diff.add(current_diff);
        }
        return diff;
    }
}
