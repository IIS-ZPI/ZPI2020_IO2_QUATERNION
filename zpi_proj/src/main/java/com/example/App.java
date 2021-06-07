package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class App {
    static ArrayList<Double> getData(String currency, int timePeriod){
        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency.toLowerCase(Locale.ROOT) + "/last/"
                + timePeriod + "/?format=xml";
        String doc_to_str = "";
        try {
            Document doc = Jsoup.connect(url).get();
            doc_to_str = doc.toString();
        } catch (Exception e) {
            System.out.println("Wrong input data");
            return null;
        }

        ArrayList<Double> data = new ArrayList<>();
        Document doc = Jsoup.parse(doc_to_str, "", Parser.xmlParser());
        for (Element e : doc.select("Mid")) {
            data.add(Double.parseDouble(e.text()));
        }

        return data;
    }

    static void showStatisticalMeasures(ArrayList<Double> data) throws IOException {
        System.out.println(new StatisticalMeasures(data));
    }

    static void showNumberOfSession(ArrayList<Double> data) throws IOException {
        System.out.println(new SessionAnalysis(data));
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> timePeriods = new HashMap<>();
        timePeriods.put(1, 7);
        timePeriods.put(2, 14);
        timePeriods.put(3, 31);
        timePeriods.put(4, 90);
        timePeriods.put(5, 182);
        timePeriods.put(6, 365);

        final String TEST_URL = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json/";
        NBP[] avalibleCurrenciesTable = (NBP[]) JsonDataGetter.getUrlData(TEST_URL, NBP[].class);
        if (avalibleCurrenciesTable == null || avalibleCurrenciesTable[0] == null) {
            System.out.println("NBP Web API service is currently not available");
            return;
        }

        List<String> avalibleCurrencies = Arrays.stream(avalibleCurrenciesTable[0].rates).map(e -> e.code)
                .collect(Collectors.toList());
        avalibleCurrencies.sort((a, b) -> a.compareTo(b));
        System.out.println(
                "1 - Ilość sesji wzrostowych, spadkowych i bez zmian w wybranym okresie i dla wybranej waluty.");
        System.out.println(
                "2 - Miary statystyczne: miediana, dominanta, odchylenie standardowe i współczynnik zmienności w wybranym okresie i dla wybranej waluty.");

        int action = 0;
        String currency = "";
        int timePeriod = 0;

        Scanner scanner = new Scanner(System.in);
        action = Integer.parseInt(scanner.nextLine());
        if (action != 1 && action != 2) {
            System.out.println("Wrong input data (operation)");
            System.exit(0);
        }

        System.out.println("Avalible currency\n" + avalibleCurrencies + "\nPlease type your currency code");
        currency = scanner.nextLine();
        if (!avalibleCurrencies.contains(currency.toUpperCase())) {
            System.out.println("Incorrect currency code");
            scanner.close();
            return;
        }
        System.out.println(
                "wpisz okres:\n1 - 1 tydzień,\n2 - 2 tygodni,\n3 - 1 miesięc,\n4 - 1 kwartał,\n5 - pół roku\n6 - 1 rok");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            timePeriod = timePeriods.get(key);
        } catch (Exception e) {
            System.out.println("Incorrect time peroid");
            scanner.close();
            return;
        }

        try {
            if (action == 1) {
                ArrayList<Double> data = getData(currency, timePeriod);
                showNumberOfSession(data);
            } else {
                ArrayList<Double> data = getData(currency, timePeriod + 1);
                showStatisticalMeasures(data);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }

        scanner.close();
    }
}
