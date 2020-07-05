package algorithm;

import Model.Customer;
import Model.Travel;

public class SimulatedAnnealing {
    private Travel travel;
    private Travel bestSolution = new Travel();
    private final int INITIAL_DISTANCE = 22186;
    private double sigma;
    private double sigmaReduction;
    private int index1, index2;
    private Customer change1, change2;


    public SimulatedAnnealing(Travel travel, double sigma, double sigmaReduction) {
        this.travel = travel;
        this.sigma = sigma;
        this.sigmaReduction = sigmaReduction;
    }

    private boolean accecptChange(int oldDistance, int newDistance, double sigma){
        if(newDistance < oldDistance){
           return true;
        }
        double delta = newDistance - oldDistance;
        double calc = Math.exp(-delta/sigma);
        if(calc > Math.random()){
            return true;
        } else
            return false;
    }

    public void runForOpmizedDistance(){
        System.out.println(travel.getDinstance());
        while (sigma > 1){
            optimizeDistance();
        }
        travel.printRoute();
        System.out.println(travel.getDinstance());
    }

    public void runForOptimizdDelivery(){
        System.out.println(travel.getDinstance());
        while (bestSolution.countDeliveredPackages() < 51) {
            travel = new Travel();
            while (sigma > 1){
                optimizeDelivery();
                if(travel.checkTimeWindows()){
                    //if(bestSolution.getDinstance() > travel.getDinstance()){
                    bestSolution = travel;
                    // }
                }
            }
        }
        System.out.println(bestSolution.getDinstance());
        bestSolution.printRoute();
        System.out.println(travel.getDinstance());
    }

    private void optimizeDistance() {
        int currentDistance = travel.getDinstance();
        swapCustomer();
        int newDistance = travel.getDinstance();

        if(!accecptChange(currentDistance, newDistance, sigma)){
            reverseSwap();
        }

        sigma *= 1-sigmaReduction;
    }

    private void optimizeDelivery(){
        int currentPackages = travel.countDeliveredPackages();
        swapCustomer();
        int newDeliveredPackages = travel.countDeliveredPackages();

        if(!accecptChange(currentPackages, newDeliveredPackages, sigma)){
            reverseSwap();
        }

        sigma *= 1-sigmaReduction;
    }

    private void swapCustomer(){
        while((index1 = (int) (travel.getTravel().length * Math.random())) ==0);
        while((index2 = (int) (travel.getTravel().length * Math.random())) ==0);

        change1 = travel.getCustomer(index1);
        change2 = travel.getCustomer(index2);

        travel.setCustomer(index1, change2);
        travel.setCustomer(index2, change1);
    }

    private void reverseSwap(){
        travel.setCustomer(index2, change2);
        travel.setCustomer(index1, change1);
    }
}
