import java.util.ArrayList;
import java.util.Arrays;
import Exceptions.*;

public class Car implements Transport{
    private static String marka;
    private static Model[] models;

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    Car(String marka, int count){
        this.marka = marka;
        this.models = new Model[count];

        for (int i = 1; i < count + 1; i++) {
            models[i-1] = new Model("Model" + i, Math.round(Math.random()*10_000_000));
        }
    }

    private class Model {
        String name;
        double cost;

        Model(String name, double cost){
            this.name = name;
            this.cost = cost;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }

    public void setNameModel(String oldName, String newName){
        if(getNameModels().contains(oldName) && oldName != newName){
            for (int i = 0; i < models.length; i++) {
                if(models[i].getName().equals(oldName)){
                    models[i].setName(newName);
                }
            }
        }
    }

    public ArrayList<String> getNameModels() {
        ArrayList<String> names = new ArrayList<>();
        for(Model model : models){
            names.add(model.getName());
        }
        return names;
    }

    public double getCostByName(String name){
        for (Model model : models) {
            return (model.getName().equals(name) ? model.getCost() : 0);
        }
        return 0;
    }

    public void setCostByName(String name, double cost){
        for (Model model : models) {
            if(model.getName().equals(name)){
                model.setCost(cost);
            }
        }
    }

    public ArrayList<Double> getCostModels(){
        ArrayList<Double> prices = new ArrayList<>();
        for (Model model : models) {
            prices.add(model.getCost());
        }
        return prices;
    }

    public void addModel(String name, double cost){
        Model[] newModels = Arrays.copyOf(models, models.length + 1);
        newModels[newModels.length-1] = new Model(name, cost);
        models = newModels;
    }

    public void deleteModel(String name){
        int index = 0;
        Model[] newModels = Arrays.copyOf(models, models.length - 1);
        if(getNameModels().contains(name)){
            for (int i = 0; i < models.length; i++) {
                if(!models[i].getName().equals(name)){
                    newModels[index] = models[i];
                    index++;
                }
            }
        }
        models = newModels;
    }

    public int getLengthModels(){
        return models.length;
    }
}
