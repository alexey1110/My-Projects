import java.util.ArrayList;
import Exceptions.*;

public class Motorcycle implements Transport{
    private int size = 0;
    private String marka;
    private long lastModified;

    public String getMarka() {
        return marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }
    public long getLastModified(){
        return lastModified;
    }
    public Model getHead() {
        return head;
    }

    private class Model {
        String name = null;
        double cost = Double.NaN;
        Model prev = null;
        Model next = null;

        Model(String name, double cost) {
            this.name = name;
            this.cost = cost;
        }

        Model() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }
    }

    private Model head = new Model();
    {
        head.prev = head;
        head.next = head;
    }

    Motorcycle(String marka, int size) throws DuplicatemodelNameException{
        setMarka(marka);
        for (int i = 0; i < size; i++) {
            addModel("Model" + (i + 1), Math.round(Math.random() * 1_000_000));
        }
    }

    public void addModel(String name, double cost) throws DuplicatemodelNameException{
        if(cost < 0){
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной");
        }
        Model model = head.next;
        while(model != head){
            if(model.getName().equals(name)){
                throw new DuplicatemodelNameException("Такая модель уже есть");
            }
            model = model.next;
        }
        Model newModel = new Model(name, cost);
        newModel.next = head;
        newModel.prev = head.prev;
        head.prev.next = newModel;
        head.prev = newModel;
        size++;
        lastModified = System.currentTimeMillis();
    }

    public void setNameModel(String oldName, String newName) throws DuplicatemodelNameException, NoSuchModelNameException{
        Model model = head.next;
        while (model.next != head) {
            if(model.getName().equals(newName)){
                throw new DuplicatemodelNameException("Такая модель уже есть");
            }
            model = model.next;
        }
        Model suchModel = head.next;
        while(suchModel != head && !suchModel.getName().equals(oldName)){
            suchModel = suchModel.next;
        }
        if(suchModel == head){
            throw new NoSuchModelNameException("Модель не найдена");
        }
        model.setName(newName);
        lastModified = System.currentTimeMillis();
    }

    public ArrayList<String> getNameModels() {
        ArrayList<String> names = new ArrayList<>();
        Model model = head.next;
        while (!model.equals(head)) {
            names.add(model.getName());
            model = model.next;
        }
        return names;
    }

    public ArrayList<Double> getCostModels() {
        ArrayList<Double> costs = new ArrayList<>();
        Model model = head.next;
        while (!model.equals(head)) {
            costs.add(model.getCost());
            model = model.next;
        }
        return costs;
    }

    public double getCostByName(String name) throws NoSuchModelNameException{
        Model model = head.next;
        while(model != head && !model.getName().equals(name)){
            model = model.next;
        }
        if(model == head){
            throw new NoSuchModelNameException("Модель не найдена");
        }
        return model.getCost();
    }

    public void setCostByName(String name, double cost) throws NoSuchModelNameException {
        if(cost < 0){
            throw new ModelPriceOutOfBoundsException("Цена не может быть отрицательной");
        }
        Model model = head.next;
        while(model != head && !model.getName().equals(name)){
            model = model.next;
        }
        if(model == head){
            throw new NoSuchModelNameException("Модель не найдена");
        }
        model.setCost(cost);
        lastModified = System.currentTimeMillis();
    }

    public void deleteModel(String name) throws NoSuchModelNameException {
        Model model = head.next;
        while(model != head && !model.getName().equals(name)){
            model = model.next;
        }
        if(model == head){
            throw new NoSuchModelNameException("Модель не найдена");
        }
        model.next.prev = model.prev;
        model.prev.next = model.next;
        size--;
        lastModified = System.currentTimeMillis();
    }

    @Override
    public int getLengthModels() {
        return size;
    }

}
