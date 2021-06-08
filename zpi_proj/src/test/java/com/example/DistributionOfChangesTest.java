package com.example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DistributionOfChangesTest {

    @Test
    public void nullTests() {
        List<Double> exampleData = new ArrayList<>();
        boolean exceptionThrown = false;
        List<Double> distribution;
        try {
            distribution = new DistributionOfChanges(exampleData).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("null data returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData = new ArrayList<>();
        try {
            distribution = new DistributionOfChanges(exampleData).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("empty data returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData.add(8.0);
        try {
            distribution = new DistributionOfChanges(exampleData).getDistribution();
        } catch (IncorrectListException e) {
            exceptionThrown = true;
        }
        assertTrue("data of size 1 returned not null", exceptionThrown);
        exceptionThrown = false;
        exampleData.add(8.0);
        distribution = new DistributionOfChanges(exampleData).getDistribution();
        assertTrue("data of size 2 (correct) returned null", distribution != null);
        exampleData.add(8.0);
        distribution = new DistributionOfChanges(exampleData).getDistribution();
        assertTrue("data of size 3 (correct) returned null", distribution != null);
        exampleData.add(1.0);
        exampleData.add(2.0);
        exampleData.add(2.0);
        exampleData.add(100.0);
        exampleData.add(13.0);
        exampleData.add(22.0);
        distribution = new DistributionOfChanges(exampleData).getDistribution();
        assertTrue("data of bigger size returned null", distribution != null);
    }

}
