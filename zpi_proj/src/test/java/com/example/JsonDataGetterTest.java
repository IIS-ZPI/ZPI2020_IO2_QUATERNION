package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//assert true check validates correct statement ex. if something should be null, assertTrue(something==null)
//in other words the check contains a statement true if everything goes allright

public class JsonDataGetterTest {

    @Test
    public void nullTests() {
        Object ob = JsonDataGetter.getUrlData("", null);
        assertTrue("empty + null returned !null", ob == null);
        ob = JsonDataGetter.getUrlData(null, null);
        assertTrue("null + null returned !null", ob == null);
        ob = JsonDataGetter.getUrlData("hello world", NBP[].class);
        assertTrue("invalid url returned !null", ob == null);
        ob = JsonDataGetter.getUrlData("https://api.nbp.pl/api/exchangerates/tables/A/?format=json/", null);
        assertTrue("valid url and null type returned !null", ob == null);
        ob = JsonDataGetter.getUrlData("https://api.nbp.pl/api/exchangerates/tables/A/?format=json/",
                NBP[].class);
        assertTrue("valid url and valid type returned null", ob != null);
    }

    @Test
    public void NBPTest() {
        String nbp_url = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json/";
        Class<?> class_type = NBP[].class;
        NBP[] array = (NBP[]) JsonDataGetter.getUrlData(nbp_url, class_type);
        assertTrue("valid parameters returned null instead of array", array != null);
        assertTrue(String.format("valid parameters returned no objects in array [%d]", array.length), array.length > 0);
        int idx = 0;
        for (NBP data : array) {
            assertTrue(String.format("object in returned array is null ~ array[%d] == null", idx), data != null);
            assertTrue("table string is null", data.table != null);
            assertTrue("no string is null", data.no != null);
            assertTrue("effectiveDate Date is null", data.effectiveDate != null);
            assertTrue("rates table is null", data.rates != null);
            int idx2 = 0;
            for (NationalRate rate : data.rates) {
                assertTrue(String.format("a rate [%d] in rates in data [%d] from array is null", idx2, idx),
                        rate != null);
                assertTrue(String.format("currency string in rate %d is null", idx2), rate.currency != null);
                assertTrue("currency string in rate is an empty string", !rate.currency.equals(""));
                assertTrue("code string in rate is null or empty", rate.currency != null && !rate.currency.equals(""));
                assertTrue("mid Double in rate is null or negative", rate.mid != null && rate.mid > 0);
            }
        }
    }

}
