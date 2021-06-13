package com.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
public class App {

    static final String NBP_NOT_AVAILABLE_INFO = "NBP Web API service is currently not available";

    static List<Double> getData(String currency,int timePeriod) throws IOException{

        LocalDate today = LocalDate.now();
        LocalDate start = today.plusDays(-timePeriod);

        String url = "http://api.nbp.pl/api/exchangerates/rates/a/" + currency.toLowerCase(Locale.ROOT) + '/'+ start + '/'+ today + "/?format=xml";
        Document doc = Jsoup.connect(url).get();
        List<Double> data = new ArrayList<>();
        doc = Jsoup.parse(doc.toString(), "", Parser.xmlParser());
        for (Element e : doc.select("Mid")) {
            data.add(Double.parseDouble(e.text()));
        }
        return data;
    }

    public static void main(String[] args) {

        final String CURRENCY_TABLE = "https://api.nbp.pl/api/exchangerates/tables/A/?format=json/";
        NBP[] avalibleCurrenciesTable = (NBP[]) JsonDataGetter.getUrlData(CURRENCY_TABLE, NBP[].class);
        if (avalibleCurrenciesTable == null || avalibleCurrenciesTable[0] == null) {
            System.out.println(NBP_NOT_AVAILABLE_INFO);
            return;
        }
        HashMap<Integer, Integer> timePeriods = new HashMap<>();
        timePeriods.put(1, 7);
        timePeriods.put(2, 14);
        timePeriods.put(3, 31);
        timePeriods.put(4, 90);
        timePeriods.put(5, 182);
        timePeriods.put(6, 365);

        List<String> avalibleCurrencies = Arrays.stream(avalibleCurrenciesTable[0].rates).map(e -> e.code)
                .collect(Collectors.toList());
        avalibleCurrencies.sort((a, b) -> a.compareTo(b));
        System.out.println(
                "1 - Ilość sesji wzrostowych, spadkowych i bez zmian w wybranym okresie i dla wybranej waluty.");
        System.out.println(
                "2 - Miary statystyczne: miediana, dominanta, odchylenie standardowe i współczynnik zmienności w wybranym okresie i dla wybranej waluty.");
        System.out.println("3 - Rozklad zmian.");

        Scanner scanner = new Scanner(System.in);
        int action = Integer.parseInt(scanner.nextLine());
        if (action != 1 && action != 2 && action != 3) {
            System.out.println("Wrong input data (operation)");
            scanner.close();
            return;
        }

        System.out.println("Avalible currency\n" + avalibleCurrencies + "\nPlease type your" + (action == 3 ? " first" : "") + " currency code");
        String currency = scanner.nextLine();
        if (!avalibleCurrencies.contains(currency.toUpperCase(Locale.getDefault()))) {
            System.out.println("Incorrect currency code");
            scanner.close();
            return;
        }
        String currency2 = null;
        if (action == 3){
            System.out.println("Type second currency code");
            currency2 = scanner.nextLine();
            if (!avalibleCurrencies.contains(currency2.toUpperCase(Locale.getDefault()))) {
                System.out.println("Incorrect currency code");
                scanner.close();
                return;
            }
        }

        System.out.println("wpisz okres:\n1 - 1 tydzień,\n2 - 2 tygodni,\n3 - 1 miesięc,\n4 - 1 kwartał,\n5 - pół roku\n6 - 1 rok");

        try {
            int key = Integer.parseInt(scanner.nextLine());
            int timePeriod = timePeriods.get(key);

            if (action == 1) {
                List<Double> data = getData(currency, timePeriod);
                System.out.println(new SessionAnalysis(data));
            } 
            else if (action == 2) {
                List<Double> data = getData(currency, timePeriod + 1);
                System.out.println(new StatisticalMeasures(data));
            }  
            else{
                List<Double> firstCurrencyList = getData(currency,timePeriod);
                List<Double> secondCurrencyList = getData(currency2,timePeriod);
                List<Distributionvalue> distributionList = new DistributionOfChanges(firstCurrencyList, secondCurrencyList).getDistribution();
                for(Distributionvalue peroid : distributionList){
                    System.out.println(peroid);
                }
            }
        }
        catch(NumberFormatException err){
            System.out.println("Incorrect time peroid");
            scanner.close();
            return;
        }catch (IOException e) {
            System.out.println(NBP_NOT_AVAILABLE_INFO);
        }

        scanner.close();
    }
}
