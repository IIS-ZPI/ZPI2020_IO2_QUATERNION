package com.example;

import java.util.ArrayList;
import java.util.List;

public class DistributionOfChanges {
    static ArrayList<Double> getDistributionOfChanges(List<Double> data) {
        if (data == null || data.size() < 2)
            return null;

        ArrayList<Double> diff = new ArrayList<>();
        for (int i = 1; i < data.size(); i++) {
            double current_diff = ((data.get(i) / data.get(i - 1)) - 1) * 100;
            diff.add(current_diff);
        }
        return diff;
    }
}
