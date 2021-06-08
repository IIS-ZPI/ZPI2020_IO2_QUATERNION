package com.example;

import java.util.ArrayList;
import java.util.List;

class SessionAnalysis {
    List<Double> data;
    int numberOfIncreasingSessions;
    int numberOfDecreasingSessions;
    int numberOfSessionsWithoutChange;

    public int getNumberOfIncreasingSessions() {
        return this.numberOfIncreasingSessions;
    }

    public int getNumberOfDecreasingSessions() {
        return this.numberOfDecreasingSessions;
    }

    public int getNumberOfSessionsWithoutChange(){
        return this.numberOfSessionsWithoutChange;
    }

    @Override
    public String toString() {
        return "Number Of Increasing Sessions: " + numberOfIncreasingSessions + "\nNumber Of Decreasing Sessions: " + numberOfDecreasingSessions + "\nNumber Of Sessions Without Change: "+ numberOfSessionsWithoutChange;

    }

    SessionAnalysis(List<Double> data) {
        if (data == null || data.isEmpty())
            throw new IncorrectListException();
        this.data = data;
        this.numberOfIncreasingSessions = calculateNumberOfIncreasingSessions();
        this.numberOfDecreasingSessions = calculateNumberOfDecreasingSessions();
        this.numberOfSessionsWithoutChange = calculateNumberOfSessionsWithoutChange();
    }

    private int calculateNumberOfIncreasingSessions(){
        int res = 0;
        for(int i = 1; i < this.data.size(); i++){
            if(this.data.get(i)>this.data.get(i-1))
                res++;
        }
        return res;
    }

    private int calculateNumberOfDecreasingSessions(){
        int res = 0;
        for(int i = 1; i < this.data.size(); i++){
            if(this.data.get(i)<this.data.get(i-1))
                res++;
        }
        return res;
    }

    private int calculateNumberOfSessionsWithoutChange(){
        int res = 0;
        for(int i = 1; i < this.data.size(); i++){
            if(this.data.get(i).equals(this.data.get(i - 1)))
                res++;
        }
        return res;
    }
}