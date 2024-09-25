import Exceptions.DuplicatemodelNameException;
import Exceptions.NoSuchModelNameException;

import java.util.ArrayList;

public interface Transport {
    void addModel(String name, double cost) throws DuplicatemodelNameException;
    void setNameModel(String oldName, String newName) throws DuplicatemodelNameException, NoSuchModelNameException;
    ArrayList<String> getNameModels();
    ArrayList<Double> getCostModels();
    double getCostByName(String name) throws NoSuchModelNameException;
    void setCostByName(String name, double cost) throws NoSuchModelNameException;
    void deleteModel(String name) throws NoSuchModelNameException;
    int getLengthModels();
}
