package com.example;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.nio.charset.StandardCharsets;

public class DataGetter {

    public class NationalRate{
        String currency;
        String code;
        Double mid;

        @Override
        public String toString() {
            return currency +' '+ code +' '+ mid;
        }
    }

    public class NBP{
        String table;
        String no;
        Date effectiveDate;
        NationalRate[] rates;

        @Override
        public String toString() {
            
            return table +' '+ no +' '+ effectiveDate +' ' + Arrays.toString(rates);
        }
    }

    public static Object getUrlData(String url,Class<?> Type){
        Object jsonParsed = null;
        try {
            Gson gson = new Gson();
            URL nbpUrl = new URL(url);
            JsonReader reader = new JsonReader(new InputStreamReader(nbpUrl.openStream(), StandardCharsets.UTF_8));
            jsonParsed = gson.fromJson(reader, Type);
        }catch(JsonSyntaxException e){
            System.out.println("Data from link could not be parsed into JSON object");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParsed;
    }

    public static void main(String[] args) throws Exception {
        final String TEST_URL = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json/";
        NBP[] a= (NBP[]) getUrlData(TEST_URL, NBP[].class);
        System.out.println(Arrays.toString(a));
    }
}
