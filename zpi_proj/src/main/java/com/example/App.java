package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.IOException;
import java.util.*;

public class App {
    static void showStatisticalMeasuresFromXML(String currency, int timePeriod) throws IOException {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency.toLowerCase(Locale.ROOT) + "/last/" + timePeriod + "/?format=xml";
        String doc_to_str = "";
        try {
            Document doc = Jsoup.connect(url).get();
            doc_to_str = doc.toString();
        }catch (Exception e){
            System.out.println("Wrong input data");
            return;
        }

        ArrayList<Double> data = new ArrayList<>();

        Document doc = Jsoup.parse(doc_to_str, "", Parser.xmlParser());
        for (Element e : doc.select("Mid")) {
            data.add(Double.parseDouble(e.text()));
        }

        System.out.println();
        System.out.println("median: "+StatisticalMeasures.getMedian(data));
        System.out.println("mode: "+StatisticalMeasures.getMode(data));
        System.out.println("standard deviation: "+StatisticalMeasures.getStandardDeviation(data));
        System.out.println("coefficient of variation: "+StatisticalMeasures.getCoefficientOfVariation(data));
    }

    static void showNumberOfSession(String currency, int timePeriod) throws IOException {
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency.toLowerCase(Locale.ROOT) + "/last/" + (timePeriod+1) + "/?format=xml";
        String doc_to_str = "";
        try {
            Document doc = Jsoup.connect(url).get();
            doc_to_str = doc.toString();
        }catch (Exception e){
            System.out.println("Wrong input data");
            return;
        }

        ArrayList<Double> data = new ArrayList<>();

        Document doc = Jsoup.parse(doc_to_str, "", Parser.xmlParser());
        for (Element e : doc.select("Mid")) {
            data.add(Double.parseDouble(e.text()));
        }

        System.out.println();
        System.out.println("ilość sesji wzrostowych: "+SessionAnalysis.getNumberOfIncreasingSessions(data));
        System.out.println("ilość sesji spadkowych: "+SessionAnalysis.getNumberOfDecreasingSessions(data));
        System.out.println("ilość sesji bez zmian: "+SessionAnalysis.getNumberOfSessionsWithoutChange(data));
    }

    public static void main(String[] args){
        HashMap<Integer, Integer> timePeriods = new HashMap<>();
        timePeriods.put(1, 7);
        timePeriods.put(2, 14);
        timePeriods.put(3, 31);
        timePeriods.put(4, 90);
        timePeriods.put(5, 182);
        timePeriods.put(6, 365);

        System.out.println("1 - Ilość sesji wzrostowych, spadkowych i bez zmian w wybranym okresie i dla wybranej waluty.");
        System.out.println("2 - Miary statystyczne: miediana, dominanta, odchylenie standardowe i współczynnik zmienności w wybranym okresie i dla wybranej waluty.");

        int action = 0;
        String currency = "";
        int timePeriod = 0;

        Scanner scanner = new Scanner(System.in);
        action = Integer.parseInt(scanner.nextLine());
        if(action != 1 && action != 2){
            System.out.println("Wrong input data (operation)");
            System.exit(0);
        }

        System.out.println("Wpisz walutę: ");
        try {
            currency = scanner.nextLine();
        }
        catch (Exception e){
            System.out.println("Wrong input data (currency)");
            System.exit(0);
        }
        System.out.println("wpisz okres:\n1 - 1 tydzień,\n2 - 2 tygodni,\n3 - 1 miesięc,\n4 - 1 kwartał,\n5 - pół roku\n6 - 1 rok");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            timePeriod = timePeriods.get(key);
        }
        catch (Exception e){
            System.out.println("Wrong input data (time period)");
            System.exit(0);
        }

        try {
            if(action == 1) {
                showNumberOfSession(currency, timePeriod);
            }
            else {
                showStatisticalMeasuresFromXML(currency, timePeriod);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        
        scanner.close();
    }
}
