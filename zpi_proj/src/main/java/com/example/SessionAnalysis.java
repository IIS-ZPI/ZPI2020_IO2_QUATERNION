// package com.example;

// import java.util.ArrayList;
// import java.util.Collections;

// public class SessionAnalysis {
//     static int getNumberOfIncreasingSessions(ArrayList<Double> data){
//         if(data==null || data.isEmpty())
//             return -1;
//         int res = 0;
//         for(int i = 1; i < data.size(); i++){
//             if(data.get(i)>data.get(i-1))
//                 res++;
//         }
//         return res;
//     }

//     static int getNumberOfDecreasingSessions(ArrayList<Double> data){
//         if(data==null || data.isEmpty())
//             return -1;
//         int res = 0;
//         for(int i = 1; i < data.size(); i++){
//             if(data.get(i)<data.get(i-1))
//                 res++;
//         }
//         return res;
//     }

//     static int getNumberOfSessionsWithoutChange(ArrayList<Double> data){
//         if(data==null || data.isEmpty())
//             return -1;
//         int res = 0;
//         for(int i = 1; i < data.size(); i++){
//             if(data.get(i).equals(data.get(i - 1)))
//                 res++;
//         }
//         return res;
//     }
// }

package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class StatisticalMeasures {
    private List<Double> list;
    private List<Double> mode;
    private double median;
    private double mean;
    private double stdDeviotion;

    StatisticalMeasures(List<Double> list){
        if (list == null || list.isEmpty())
            throw new IncorrectCalculation();
        this.list = list;
        this.mean = calculateMean();
        this.mode = calulateMode();
        this.median = calculateMedian();
        this.stdDeviotion = calculateCoefficientOfVariation();
    }
    class IncorrectCalculation extends RuntimeException {
        IncorrectCalculation(String errorMessage, Throwable err) {
            super(errorMessage, err);
        }
        IncorrectCalculation() { 
            super();
        }
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

    private ArrayList<Double> calulateMode() {
      
        Map<Double,Integer> occurences = new HashMap<>(); 
        
        for(Double val : list){
            occurences.merge(val, 1, Integer::sum);
        }


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

    private double calculateMean() {
        double sum = 0.0;
        for (double num : list) {
            sum += num;
        }
        return sum / list.size();
    }

    private double getStandardDeviation() {
        if (list == null || list.isEmpty())
            return -1;
        double standardDeviation = 0.0;

        for (double num : list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / list.size());
    }

    private double calculateCoefficientOfVariation(){
        if(list==null || list.isEmpty())
            return -1;
        if (mean == 0)
            return getStandardDeviation();
        return getStandardDeviation()/mean;
    }
}
