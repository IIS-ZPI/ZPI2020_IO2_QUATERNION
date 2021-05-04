import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class App {
    static double getMedian(ArrayList<Double> list){
        Collections.sort(list);
        double median;
        if (list.size() % 2 == 0)
            median = ((double)list.get(list.size()/2) + ((double)list.get(list.size()/2 - 1)))/2;
        else
            median = (double) list.get(list.size()/2);
        return median;
    }

    static double getMode(ArrayList<Double> list){
        double maxValue = 0;
        int maxCount = 0;

        for (int i = 0; i < list.size(); ++i) {
            int count = 0;
            for (int j = 0; j< list.size(); j++) {
                if (list.get(j).equals(list.get(i)) && i != j)
                    ++count;
            }

            if (count > maxCount) {
                maxCount = count;
                maxValue = list.get(i);
            }
        }
        return maxValue;
    }

    static double getMean(ArrayList<Double> list){
        double sum = 0.0;

        for(double num : list) {
            sum += num;
        }

        return sum/list.size();
    }

    static double getStandardDeviation(ArrayList<Double> list){
        double standardDeviation = 0.0;
        double mean = getMean(list);

        for(double num: list) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/list.size());
    }

    static double getCoefficientOfVariation(ArrayList<Double> list){
        return (getStandardDeviation(list) / getMean(list));
    }

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

        ArrayList<Double> mids = new ArrayList<>();

        Document doc2 = Jsoup.parse(doc_to_str, "", Parser.xmlParser());
        for (Element e : doc2.select("Mid")) {
            mids.add(Double.parseDouble(e.text()));
        }

        System.out.println();
        System.out.println("median: "+getMedian(mids));
        System.out.println("mode: "+getMode(mids));
        System.out.println("standard deviation: "+getStandardDeviation(mids));
        System.out.println("coefficient of variation: "+getCoefficientOfVariation(mids));
    }

    public static void main(String[] args) throws Exception {
        HashMap<Integer, Integer> timePeriods = new HashMap<>();
        timePeriods.put(1, 7);
        timePeriods.put(2, 14);
        timePeriods.put(3, 31);
        timePeriods.put(4, 90);
        timePeriods.put(5, 182);
        timePeriods.put(6, 365);

        System.out.println("miary statystyczne: miediana, dominanta, odchylenie standardowe i współczynnik zmienności za wybrany okres i dla wybranej wality");

        String currency = "";
        int timePeriod = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("wpisz walutę: ");
        try {
            currency = scanner.nextLine();
        }
        catch (Exception e){
            System.exit(0);
        }
        System.out.println("wpisz okres:\n1 - 1 tydzień,\n2 - 2 tygodni,\n3 - 1 miesięc,\n4 - 1 kwartał,\n5 - pół roku\n6 - 1 rok");
        try {
            int key = Integer.parseInt(scanner.nextLine());
            timePeriod = timePeriods.get(key);
        }
        catch (Exception e){
            System.exit(0);
        }

        showStatisticalMeasuresFromXML(currency, timePeriod);
    }
}
