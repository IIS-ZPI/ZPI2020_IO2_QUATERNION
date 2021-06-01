package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DistributionOfChangesTest {

    @Test
    public void nullTests() {
        List<Double> distribution = DistributionOfChanges.getDistributionOfChanges(null);
        assertTrue("null data returned not null", distribution == null);
        List<Double> exampleData = new ArrayList<>();
        distribution = DistributionOfChanges.getDistributionOfChanges(exampleData);
        assertTrue("empty data returned not null", distribution == null);
        exampleData.add(8.0);
        distribution = DistributionOfChanges.getDistributionOfChanges(exampleData);
        assertTrue("data of size 1 returned not null", distribution == null);
        exampleData.add(8.0);
        distribution = DistributionOfChanges.getDistributionOfChanges(exampleData);
        assertTrue("data of size 2 (correct) returned null", distribution != null);
        exampleData.add(8.0);
        distribution = DistributionOfChanges.getDistributionOfChanges(exampleData);
        assertTrue("data of size 3 (correct) returned null", distribution != null);
        exampleData.add(1.0);
        exampleData.add(2.0);
        exampleData.add(2.0);
        exampleData.add(100.0);
        exampleData.add(13.0);
        exampleData.add(22.0);
        distribution = DistributionOfChanges.getDistributionOfChanges(exampleData);
        assertTrue("data of bigger size returned null", distribution != null);
    }

}
