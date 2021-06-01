package com.example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StatisticalMeasuresTest {
    @Test
    public void getMedianTests() {
        double ERROR = -1;
        double median = StatisticalMeasures.getMedian(null);
        assertTrue("median(null) returned not -1", median == ERROR);

        List<Double> exampleData = new ArrayList<>();
        median = StatisticalMeasures.getMedian(exampleData);
        assertTrue("median(list empty) returned not -1", median == ERROR);

        exampleData.add(1.0);
        median = StatisticalMeasures.getMedian(exampleData);
        assertTrue("median of [1.0] returned not 1.0", median == 1.0);

        exampleData.add(2.0);
        median = StatisticalMeasures.getMedian(exampleData);
        assertTrue("median of [1.0, 2.0] returned not 1.5", median == 1.5);

        exampleData.add(-1.0);
        exampleData.add(-2.0);
        median = StatisticalMeasures.getMedian(exampleData);
        System.out.println(median);
        System.out.println(exampleData);
        assertTrue("median of [-2, -1, 1, 2] returned not 0", median == 0.0);

        exampleData.add(100.0);
        exampleData.add(-100.0);
        List<Double> exampleCopy = new ArrayList<>(exampleData);
        median = StatisticalMeasures.getMedian(exampleData);
        assertTrue("getMedian modified given list", exampleCopy.size() == exampleData.size());
        for (int i = 0; i < exampleCopy.size(); i++)
            assertTrue("getMedian modified given list", exampleCopy.get(i) == exampleData.get(i));

        exampleData = new ArrayList<>();
        exampleCopy = null;
        for (int i = 1; i < 10; i++)
            exampleData.add(Double.valueOf(i));
        median = StatisticalMeasures.getMedian(exampleData);
        assertTrue("median returned wrong value", 5.5 == median);
    }

    @Test
    public void getMeanTest() {
        double ERROR = -1;
        double mean = StatisticalMeasures.getMean(null);
        assertTrue("mean(null) returned not -1", mean == ERROR);

        List<Double> exampleData = new ArrayList<>();
        mean = StatisticalMeasures.getMean(exampleData);
        assertTrue("mean(list empty) returned not -1", mean == ERROR);

        exampleData.add(1.0);
        mean = StatisticalMeasures.getMean(exampleData);
        assertTrue("mean of [1.0] returned not 1.0", mean == 1.0);

        exampleData.add(2.0);
        mean = StatisticalMeasures.getMean(exampleData);
        assertTrue("mean of [1.0, 2.0] returned not 1.5", mean == 1.5);

        exampleData.add(-1.0);
        exampleData.add(-2.0);
        mean = StatisticalMeasures.getMean(exampleData);
        System.out.println(mean);
        System.out.println(exampleData);
        assertTrue("mean of [-2, -1, 1, 2] returned not 0", mean == 0.0);

        exampleData.add(100.0);
        exampleData.add(-100.0);
        exampleData.add(1000.0);
        List<Double> exampleCopy = new ArrayList<>(exampleData);
        mean = StatisticalMeasures.getMean(exampleData);
        assertTrue("getMean modified given list", exampleCopy.size() == exampleData.size());
        for (int i = 0; i < exampleCopy.size(); i++)
            assertTrue("getMean modified given list", exampleCopy.get(i) == exampleData.get(i));

        exampleData = new ArrayList<>();
        exampleCopy = null;
        for (int i = -100; i <= 100; i++)
            exampleData.add(Double.valueOf(i));
        mean = StatisticalMeasures.getMean(exampleData);
        assertTrue("mean returned wrong value", 0.0 == mean);
    }

    @Test
    public void getModeTest() {
        double ERROR = -1;
        double mode = StatisticalMeasures.getMode(null);
        assertTrue("mean(null) returned not -1", mode == ERROR);

        List<Double> exampleData = new ArrayList<>();
        mode = StatisticalMeasures.getMode(exampleData);
        assertTrue("mean(list empty) returned not -1", mode == ERROR);

        exampleData.add(1.0);
        mode = StatisticalMeasures.getMode(exampleData);
        assertTrue("mean of [1.0] returned not 1.0", mode == 1.0);

        exampleData.add(2.0);
        mode = StatisticalMeasures.getMode(exampleData);
        assertTrue("mean of [1.0, 2.0] returned not 1.0", mode == 1.0);

        exampleData.add(-1.0);
        exampleData.add(-2.0);
        exampleData.add(-2.0);
        mode = StatisticalMeasures.getMode(exampleData);
        System.out.println(mode);
        System.out.println(exampleData);
        assertTrue("mean of [-2, -2, -1, 1, 2] returned not -2.0", mode == -2.0);

        exampleData.add(100.0);
        exampleData.add(-100.0);
        exampleData.add(1000.0);
        List<Double> exampleCopy = new ArrayList<>(exampleData);
        mode = StatisticalMeasures.getMode(exampleData);
        assertTrue("getMode modified given list", exampleCopy.size() == exampleData.size());
        for (int i = 0; i < exampleCopy.size(); i++)
            assertTrue("getMode modified given list", exampleCopy.get(i) == exampleData.get(i));

        exampleData = new ArrayList<>();
        exampleCopy = null;
        for (int i = -100; i <= 100; i++)
            exampleData.add(Double.valueOf(i));
        exampleData.add(55.0);
        mode = StatisticalMeasures.getMode(exampleData);
        assertTrue("mean returned wrong value", 55.0 == mode);
    }
}
