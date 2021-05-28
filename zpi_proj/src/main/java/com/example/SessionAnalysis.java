package data_analysis;

import java.util.ArrayList;
import java.util.Collections;

public class SessionAnalysis {
    static int getNumberOfIncreasingSessions(ArrayList<Double> data){
        if(data==null || data.isEmpty())
            return -1;
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i)>data.get(i-1))
                res++;
        }
        return res;
    }

    static int getNumberOfDecreasingSessions(ArrayList<Double> data){
        if(data==null || data.isEmpty())
            return -1;
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i)<data.get(i-1))
                res++;
        }
        return res;
    }

    static int getNumberOfSessionsWithoutChange(ArrayList<Double> data){
        if(data==null || data.isEmpty())
            return -1;
        int res = 0;
        for(int i = 1; i < data.size(); i++){
            if(data.get(i).equals(data.get(i - 1)))
                res++;
        }
        return res;
    }
}
