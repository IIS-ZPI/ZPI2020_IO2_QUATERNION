package data_analysis;

import java.util.ArrayList;
import java.util.Collections;

public class SessionAnalysis {
    static double getNumberOfIncreasingSessions(ArrayList<Double> data){
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i)>data.get(i-1))
                res++;
        }
        return res;
    }

    static double getNumberOfDecreasingSessions(ArrayList<Double> data){
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i)<data.get(i-1))
                res++;
        }
        return res;
    }

    static double getNumberOfSessionsWithoutChange(ArrayList<Double> data){
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i).equals(data.get(i - 1)))
                res++;
        }
        return res;
    }
}
