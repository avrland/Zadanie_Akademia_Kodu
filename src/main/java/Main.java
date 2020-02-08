import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    static PrintWriter zapis;
    public static void main(String[] args) throws IOException {
        zapis = new PrintWriter("results.txt");
        zapis.println("Zadanie Akademia kodu:");
        zapis.println("######################");
        zadanie readfile = new zadanie();
        readfile.getMoreHappierThanPoland();
        readfile.getFreedomCountries();
        readfile.getLeastCorruptedCountries();
        zapis.close();
    }
}

class zadanie {

    String[] HEADERS = { "Country (region)", "Freedom", "Positive affect", "Corruption"};
    public Iterable<CSVRecord> getRecords() throws IOException {
        Reader in = new FileReader("happiness.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        return records;
    }
    int polandAffect, currentAffect;
    public void getMoreHappierThanPoland() throws IOException {
        //Get Poland happiness value
        Iterable<CSVRecord> records = getRecords();
        for (CSVRecord record : records) {
            String country = record.get("Country (region)");
            String affect = record.get("Positive affect");
            if(country.equals("Poland")){
                polandAffect = Integer.parseInt(affect);
            }
        }
        //Get more than Poland happiness countries
        Iterable<CSVRecord> records2 = getRecords();
        Main.zapis.println("Kraje szczęśliwsze od Polski:");
        System.out.println("Kraje szczęśliwsze od Polski:");
        for (CSVRecord record : records2) {
            String country = record.get("Country (region)");
            String affect = record.get("Positive affect");
            if(!affect.equals("")) currentAffect = Integer.parseInt(affect);
            if(currentAffect>polandAffect){
                System.out.println(country + " " + affect);
                Main.zapis.println(country + " " + affect);
            }
        }
        Main.zapis.println("######################");
    }

    public void getFreedomCountries() throws IOException {
        System.out.println("5 krajów z największą wolnością:");
        Main.zapis.println("5 krajów z największą wolnością:");
        List<freedom> lista = new ArrayList<freedom>();
        Iterable<CSVRecord> records = getRecords();
        for (CSVRecord record : records) {
            String country = record.get("Country (region)");
            String affect = record.get("Freedom");
            if(!affect.equals("")) lista.add(new freedom(country, Integer.parseInt(affect)));
        }
        Collections.sort(lista, new Comparator<freedom>() {
            @Override
            public int compare(freedom u1, freedom u2) {
                return Integer.compare(u1.getValue(), u2.getValue());
            }
         });
        Collections.reverse(lista);
        for(int i = 0; i < 5; i++) {
            System.out.println(lista.get(i).country + " " + lista.get(i).getValue());
            Main.zapis.println(lista.get(i).country + " " + lista.get(i).getValue());
        }
        Main.zapis.println("######################");
    }
    public class freedom {
        String country;
        int value;
        freedom(String p1, int p2) {
            country = p1;
            value = p2;
        }
        int getValue(){
            return value;
        }
        String geCountryName(){
            return country;
        }
        Comparator<freedom> compareByFreedom = new Comparator<freedom>() {
            @Override
            public int compare(freedom o1, freedom o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        };
    }
    public void getLeastCorruptedCountries() throws IOException {
        System.out.println("5 krajów z najmniejszą korupcją:");
        Main.zapis.println("5 krajów z najmniejszą korupcją:");
        List<corruption> lista = new ArrayList<corruption>();
        Iterable<CSVRecord> records = getRecords();
        for (CSVRecord record : records) {
            String country = record.get("Country (region)");
            String affect = record.get("Corruption");
            if(!affect.equals("")) lista.add(new corruption(country, Integer.parseInt(affect)));
        }
        Collections.sort(lista, new Comparator<corruption>() {
            @Override
            public int compare(corruption u1, corruption u2) {
                return Integer.compare(u1.getValue(), u2.getValue());
            }
        });
        for(int i = 0; i < 5; i++) {
            System.out.println(lista.get(i).country + " " + lista.get(i).getValue());
            Main.zapis.println(lista.get(i).country + " " + lista.get(i).getValue());
        }
        Main.zapis.println("######################");
    }

    public class corruption {
        String country;
        int value;
        corruption(String p1, int p2) {
            country = p1;
            value = p2;
        }
        int getValue(){
            return value;
        }
        String geCountryName(){
            return country;
        }
        Comparator<corruption> compareByCorruption = new Comparator<corruption>() {
            @Override
            public int compare(corruption o1, corruption o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        };
    }

}