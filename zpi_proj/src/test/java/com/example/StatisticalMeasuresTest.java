package com.example;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StatisticalMeasuresTest {

    public void exceptionTesting() {
        boolean exceptionThrown = false;
        try {
            new StatisticalMeasures(null);
        } catch (IncorrectListException ic) {
            exceptionThrown = true;
        }
        assertTrue("StatisticalMeasures(null) didn't throw an exception", exceptionThrown);
        exceptionThrown = false;

        List<Double> exampleData = new ArrayList<>();
        try {
            new StatisticalMeasures(exampleData);
        } catch (IncorrectListException ic) {
            exceptionThrown = true;
        }
        assertTrue("StatisticalMeasures(list empty) didn't throw an exception", exceptionThrown);
    }

    @Test
    public void getMedianTests() {
        double median;
        List<Double> exampleData = new ArrayList<>();

        try {
            exampleData.add(1.0);
            median = new StatisticalMeasures(exampleData).getMedian();
            assertTrue("median of [1.0] returned not 1.0", median == 1.0);

            exampleData.add(2.0);
            median = new StatisticalMeasures(exampleData).getMedian();
            assertTrue("median of [1.0, 2.0] returned not 1.5", median == 1.5);

            exampleData.add(-1.0);
            exampleData.add(-2.0);
            median = new StatisticalMeasures(exampleData).getMedian();
            assertTrue("median of [-2, -1, 1, 2] returned not 0", median == 0.0);

            exampleData.add(100.0);
            exampleData.add(-100.0);
            List<Double> exampleCopy = new ArrayList<>(exampleData);
            median = new StatisticalMeasures(exampleData).getMedian();
            assertTrue("getMedian modified given list", exampleCopy.size() == exampleData.size());
            for (int i = 0; i < exampleCopy.size(); i++)
                assertTrue("getMedian modified given list", exampleCopy.get(i) == exampleData.get(i));

            exampleData = new ArrayList<>();
            exampleCopy = null;
            for (int i = 1; i <= 10; i++)
                exampleData.add(Double.valueOf(i));
            median = new StatisticalMeasures(exampleData).getMedian();
            assertTrue("median returned wrong value", 5.5 == median);
        } catch (IncorrectListException ic) {
            System.out.println(ic.getMessage());
            assertTrue("exception thrown with correct data", true);
        }
    }

    @Test
    public void getMeanTest() {
        double mean;
        List<Double> exampleData = new ArrayList<>();
        try {
            exampleData.add(1.0);
            mean = new StatisticalMeasures(exampleData).getMean();
            assertTrue("mean of [1.0] returned not 1.0", mean == 1.0);

            exampleData.add(2.0);
            mean = new StatisticalMeasures(exampleData).getMean();
            assertTrue("mean of [1.0, 2.0] returned not 1.5", mean == 1.5);

            exampleData.add(-1.0);
            exampleData.add(-2.0);
            mean = new StatisticalMeasures(exampleData).getMean();
            assertTrue("mean of [-2, -1, 1, 2] returned not 0", mean == 0.0);

            exampleData.add(100.0);
            exampleData.add(-100.0);
            exampleData.add(1000.0);
            List<Double> exampleCopy = new ArrayList<>(exampleData);
            mean = new StatisticalMeasures(exampleData).getMean();
            assertTrue("getMean modified given list", exampleCopy.size() == exampleData.size());
            for (int i = 0; i < exampleCopy.size(); i++)
                assertTrue("getMean modified given list", exampleCopy.get(i) == exampleData.get(i));

            exampleData = new ArrayList<>();
            exampleCopy = null;
            for (int i = -100; i <= 100; i++)
                exampleData.add(Double.valueOf(i));
            mean = new StatisticalMeasures(exampleData).getMean();
            assertTrue("mean returned wrong value", 0.0 == mean);
        } catch (IncorrectListException ic) {
            System.out.println(ic.getMessage());
            assertTrue("exception thrown with correct data", true);
        }
    }

    @Test
    public void getStdDeviationTest() {
        double stdDev;
        List<Double> exampleData = new ArrayList<>();
        try {
            exampleData.add(1.0);
            stdDev = new StatisticalMeasures(exampleData).getStdDeviation();
            assertTrue("stdDeviation of [1.0] returned not 1.0", stdDev == 0.0);

            exampleData.add(2.0);
            stdDev = new StatisticalMeasures(exampleData).getStdDeviation();
            assertTrue("stdDeviation of [1.0, 2.0] returned not 0.5", stdDev == 0.5);

            exampleData.add(-1.0);
            exampleData.add(-2.0);
            stdDev = new StatisticalMeasures(exampleData).getStdDeviation();
            assertTrue("stdDeviation of [-2, -1, 1, 2] returned not around 1.5811388300842",
                    stdDev > 1.58 && stdDev < 1.59);

            exampleData.add(100.0);
            exampleData.add(-100.0);
            exampleData.add(1000.0);
            List<Double> exampleCopy = new ArrayList<>(exampleData);
            stdDev = new StatisticalMeasures(exampleData).getStdDeviation();
            assertTrue("getstdDeviation modified given list", exampleCopy.size() == exampleData.size());
            for (int i = 0; i < exampleCopy.size(); i++)
                assertTrue("getstdDeviation modified given list", exampleCopy.get(i) == exampleData.get(i));

            exampleData = new ArrayList<>();
            exampleCopy = null;
            for (int i = -100; i <= 100; i++)
                exampleData.add(Double.valueOf(i));
            stdDev = new StatisticalMeasures(exampleData).getStdDeviation();
            assertTrue("stdDeviation returned wrong value", stdDev > 58.02 && stdDev < 58.03);
        } catch (IncorrectListException ic) {
            System.out.println(ic.getMessage());
            assertTrue("exception thrown with correct data", true);
        }
    }

    @Test
    public void getModeTest() {
        List<Double> mode;
        List<Double> exampleData = new ArrayList<>();
        try {
            exampleData.add(1.0);
            mode = new StatisticalMeasures(exampleData).getMode();
            assertTrue("mode of [1.0] returned not [1.0]", adHocListEqualsArray(mode, new Double[] { 1.0 }, false));

            exampleData.add(2.0);
            mode = new StatisticalMeasures(exampleData).getMode();
            assertTrue("mode of [1.0, 2.0] returned not [1.0, 2.0]",
                    adHocListEqualsArray(mode, new Double[] { 1.0, 2.0 }, false));

            exampleData.add(-1.0);
            exampleData.add(-1.0);
            exampleData.add(-2.0);
            exampleData.add(-2.0);
            mode = new StatisticalMeasures(exampleData).getMode();
            assertTrue("mode of [-2, -2, -1, 1, 2] returned not [-2.0, -1.0]",
                    adHocListEqualsArray(mode, new Double[] { -2.0, -1.0 }, false));

            exampleData.add(100.0);
            exampleData.add(-100.0);
            exampleData.add(1000.0);
            List<Double> exampleCopy = new ArrayList<>(exampleData);
            mode = new StatisticalMeasures(exampleData).getMode();
            assertTrue("getMode modified given list", exampleCopy.size() == exampleData.size());
            for (int i = 0; i < exampleCopy.size(); i++)
                assertTrue("getMode modified given list", exampleCopy.get(i) == exampleData.get(i));

            exampleData = new ArrayList<>();
            exampleCopy = null;
            for (int i = -100; i <= 100; i++)
                exampleData.add(Double.valueOf(i));
            exampleData.add(55.0);
            exampleData.add(44.0);
            mode = new StatisticalMeasures(exampleData).getMode();
            assertTrue("mode returned wrong value", adHocListEqualsArray(mode, new Double[] { 55.0, 44.0 }, false));
        } catch (IncorrectListException ic) {
            System.out.println(ic.getMessage());
            assertTrue("exception thrown with correct data", true);
        }
    }

    private static boolean adHocListEqualsArray(List<Double> list, Double[] array, boolean orderMatters) {
        if (list == null || array == null || list.size() != array.length)
            return false;
        if (orderMatters) {
            for (int i = 0; i < list.size(); i++)
                if (list.get(i) != array[i])
                    return false;
            return true;
        }
        List<Double> arrList = Arrays.asList(array);
        return list.containsAll(arrList) && arrList.containsAll(list);
    }
}
