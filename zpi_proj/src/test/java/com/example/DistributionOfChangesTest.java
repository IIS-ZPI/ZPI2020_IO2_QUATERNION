package com.example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays; 
import java.util.List;

import org.junit.Test;

public class DistributionOfChangesTest {

    @Test
    public void nullTests() {
        if (!DistributionOfChanges.readyToBeTested)
            return;
        List<Double> exampleData = null;
        List<Double> exampleData2 = null;
        boolean exceptionThrown = false;
        List<Distributionvalue> distribution;
        try {
            distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("null data returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData = new ArrayList<>();
        exampleData2 = new ArrayList<>();
        try {
            distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("empty data returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData.add(8.0);
        exampleData2.add(4.0);
        try {
            distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("data of size 1 returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData.add(8.0);
        exampleData2.add(4.0);
        distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        assertTrue("data of size 2 (correct) returned null", distribution != null);
        exampleData.add(8.0);
        exampleData2.add(4.0);
        distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        assertTrue("data of size 3 (correct) returned null", distribution != null);
        exampleData.addAll(Arrays.asList(new Double[] { 1.0, 2.0, 2.0, 100.0, 13.0, 22.0 }));
        exampleData2.addAll(Arrays.asList(new Double[] { 1.0, 2.0, 2.0, 100.0, 13.0, 22.0 }));
        distribution = new DistributionOfChanges(exampleData, exampleData2).getDistribution();
        assertTrue("data of bigger size returned null", distribution != null);
    }

}
