import java.util.ArrayList;
public class TransportAbs {
    public static double getAverage(Transport transport){
        ArrayList<Double> costs = transport.getCostModels();
        double sum = 0;
        for (Double cost:costs) {
            sum += cost;
        }
        return sum/costs.size();
    }
    public static void printNamesModels(Transport transport){
        ArrayList<String> names = transport.getNameModels();
        for (String name:names) {
            System.out.println(name);
        }
    }
    public static void printCostsModels(Transport transport){
        ArrayList<Double> costs = transport.getCostModels();
        for (Double cost:costs) {
            System.out.println(cost);
        }
    }
}
