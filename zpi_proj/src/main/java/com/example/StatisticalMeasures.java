package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatisticalMeasures {

    static double getMedian(List<Double> list) {
        if (list == null || list.isEmpty())
            return -1;
        List<Double> listCopy = new ArrayList<>(list);
        Collections.sort(listCopy);
        double median;
        if (listCopy.size() % 2 == 0)
            median = (listCopy.get(listCopy.size() / 2) + (listCopy.get(listCopy.size() / 2 - 1))) / 2;
        else
            median = listCopy.get(listCopy.size() / 2);
        return median;
    }

    static double getMode(List<Double> list) {
        if (list == null || list.isEmpty())
            return -1;
        double maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < list.size(); ++i) {
            int count = 0;
            for (int j = 0; j < list.size(); j++) {
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

    static double getMean(List<Double> list) {
        if (list == null || list.isEmpty())
            return -1;
        double sum = 0.0;

        for (double num : list) {
            sum += num;
        }

        return sum / list.size();
    }

    static double getStandardDeviation(List<Double> list) {
        if (list == null || list.isEmpty())
            return -1;
        double standardDeviation = 0.0;
        double mean = getMean(list);

        for (double num : list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / list.size());
    }

    static double getCoefficientOfVariation(List<Double> list) {
        if (list == null || list.isEmpty())
            return -1;
        return (getStandardDeviation(list) / getMean(list));
    }
}
