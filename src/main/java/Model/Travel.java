package Model;

import data.ReadDataFromCsv;

import java.time.LocalTime;
import java.util.ArrayList;

public class Travel {

    private Customer[] travel;

    public Travel(){
       ArrayList<Customer> customers =  ReadDataFromCsv.generateCustomer();

       travel = new Customer[52];
       for (int i = 0 ; i < travel.length; i++){
           travel[i] = customers.get(i);
       }
    }

    public Customer[] getTravel() {
        return travel;
    }

    public Customer getCustomer(int position){
        return travel[position];
    }

    public void setCustomer(int position, Customer customer){
        travel[position] = customer;
    }

    public int getDinstance(){
        int distance = 0;
        for (int i = 0; i < travel.length; i++){
            Customer start = getCustomer(i);
            Customer goal;
            if(i+1 < travel.length){
                goal = getCustomer(i+1);
            } else{
                goal = getCustomer(0);
            }
            distance += start.distanceToCustomerByEuklid(goal);
        }
        return distance;
    }

    public boolean checkTimeWindows(){
        boolean travelApproved = false;
        LocalTime currentTime = LocalTime.of(14, 00, 00);
        Customer goal;
        for(int i = 0; i < travel.length; i++){
            double distance = 0;
            if (i+1 < travel.length){
                goal = getCustomer(i+1);
            } else {
                goal = getCustomer(0);
            }
            distance = getCustomer(i).distanceToCustomerByEuklid(goal);
            double seconds = distance / 2.8;
            currentTime = currentTime.plusSeconds(new Double(seconds).longValue());
            if(currentTime.isBefore(goal.getTimespane().getEnd()) && currentTime.isAfter(goal.getTimespane().getStart())){
                travelApproved = true;
            } else {
                return false;
            }
            currentTime= currentTime.plusSeconds(240);
        }
        System.out.println("Ankunftszeit: " + currentTime);
        return travelApproved;
    }

    public int countDeliveredPackages(){
        int counter = 0;
        Customer goal;
        LocalTime currentTime = LocalTime.of(14, 00, 00);
        for(int i = 0; i < travel.length; i++){
            double distance = 0;
            if (i+1 < travel.length){
                goal = getCustomer(i+1);
            } else {
                goal = getCustomer(0);
            }
            distance = getCustomer(i).distanceToCustomerByEuklid(goal);
            double seconds = distance / 2.8;
            currentTime = currentTime.plusSeconds(new Double(seconds).longValue());
            if(currentTime.isBefore(goal.getTimespane().getEnd().plusSeconds(1)) && currentTime.isAfter(goal.getTimespane().getStart().minusSeconds(1))){
                counter++;
            }

            currentTime= currentTime.plusSeconds(240);
        }
        if(counter > 39){
            System.out.println(counter);
            printRoute();
            System.out.println(currentTime);
        }
        return counter;
    }

    private int round(double seconds) {
        int number = Integer.parseInt(String.valueOf(Math.pow(10, 0)));
        return Integer.parseInt(String.valueOf(Math.round(seconds * number) /number));
    }

    public void printRoute(){
       for(int i = 0; i < travel.length; i++){
           System.out.print(getCustomer(i).getId()+" -> ");
       }
       System.out.println("1");
    }

}
