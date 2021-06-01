package com.example;
import java.io.InputStreamReader;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import java.nio.charset.StandardCharsets;

public class JsonDataGetter {

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
}
