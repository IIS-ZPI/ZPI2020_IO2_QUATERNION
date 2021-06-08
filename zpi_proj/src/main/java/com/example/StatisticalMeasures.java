package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class StatisticalMeasures {
    private List<Double> list;
    private List<Double> mode;
    private double median;
    private double mean;
    private double stdDeviation;
    private double coeVar;

    public List<Double> getMode() {
        return this.mode;
    }

    public double getMedian() {
        return this.median;
    }

    public double getMean() {
        return this.mean;
    }

    public double getStdDeviation() {
        return this.stdDeviation;
    }

    public double getCoeVar(){
        return this.coeVar;
    }

    @Override
    public String toString() {
        return "median: " + median + "\nmean: " + mean + "\nmode: "+ mode+ "\nstdDeviotion: " + stdDeviation;
       
    }

    StatisticalMeasures(List<Double> list) {
        if (list == null || list.isEmpty())
            throw new IncorrectListException();
        this.list = list;
        this.mean = calculateMean();
        this.mode = calсulateMode();
        this.median = calculateMedian();
        this.stdDeviation = getStandardDeviation();
        this.coeVar = calculateCoefficientOfVariation();
    }

    private double calculateMedian() {
        List<Double> temp = new ArrayList<>(list);
        Collections.sort(temp);
        if (temp.size() % 2 == 0)
            median = (temp.get(temp.size() / 2) + (temp.get(temp.size() / 2 - 1))) / 2;
        else
            median = temp.get(temp.size() / 2);
        return median;
    }

    private List<Double> calсulateMode() {

        Map<Double,Integer> occurences = new HashMap<>();
        for (Double val : list) {
            occurences.merge(val, 1, Integer::sum);
        }

        Integer max = occurences.entrySet().stream()
                .max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getValue();

        List<Double> listOfMax = occurences.entrySet().stream().filter(entry -> (entry.getValue()).equals(max))
                .map(Map.Entry::getKey).collect(Collectors.toList());

        return listOfMax;
    }

    private double calculateMean() {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return sum / list.size();
    }

    private double getStandardDeviation() {
        double standardDeviation = 0.0;
        for (double num : list) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / list.size());
    }

    private double calculateCoefficientOfVariation() {
        if (mean == 0)
            return getStandardDeviation();
        return getStandardDeviation() / mean;
    }
}
