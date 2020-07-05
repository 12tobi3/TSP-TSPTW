package data;

import Model.Customer;
import Model.Timespan;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;

public class ReadDataFromCsv {
    private static CSVReader csvReader = null;
    private static String path;

    public static ArrayList<Customer> generateCustomer() {

        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Timespan> timespans = generateTimeSpans();
        path = "/Users/Tobi/Dev/htw.logistik.tsptw/src/main/resources/locations.csv";
        try {
            csvReader = new CSVReader(new FileReader(path));
            String[] customerCoordinates = null;
            while((customerCoordinates = csvReader.readNext())!=null){
                Customer customer = new Customer(Integer.parseInt(customerCoordinates[0]), Integer.parseInt(customerCoordinates[1]), customerCoordinates[2]);
                customers.add(customer);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        for(int i = 0; i < customers.size(); i++){
            customers.get(i).setTimespane(timespans.get(i));
        }


        return customers;
    }

    public static ArrayList<Timespan> generateTimeSpans(){
        path = "/Users/Tobi/Dev/htw.logistik.tsptw/src/main/resources/timespans.csv";
        ArrayList<Timespan> timespans = new ArrayList<>();
        try {
            csvReader = new CSVReader(new FileReader(path),';');
            String[] timespanDetails = null;
            while((timespanDetails = csvReader.readNext())!=null){
                Timespan timespan = new Timespan(timespanDetails[0], (timespanDetails[1]));
                timespans.add(timespan);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timespans;
    }
}
