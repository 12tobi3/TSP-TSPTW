import Model.Travel;
import algorithm.SimulatedAnnealing;

public class runAlgorithm {
    public static void main(String args[]){
        SimulatedAnnealing sa = new SimulatedAnnealing(new Travel(), 10000, 0.000001);
        sa.runForOptimizdDelivery();
    }
}
