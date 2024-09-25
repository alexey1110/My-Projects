import Exceptions.DuplicatemodelNameException;
import Exceptions.NoSuchModelNameException;

public class Main {
    public static void main(String[] args) throws DuplicatemodelNameException, NoSuchModelNameException {
//        Car car1 = new Car("BMW", 5);
//        System.out.println(car1.getNameModels());
//        System.out.println(car1.getCostModels());

//        car1.setNameModel("Model2", "Model2");
//        System.out.println(car1.getNameModels());

//        System.out.println(car1.getCostByName("Model1"));

//        car1.setCostByName("Model1", 1_000_000.0);
//        System.out.println(car1.getCostModels());

//        car1.addModel("ModelX",5_000_000);
//        System.out.println(car1.getNameModels());
//        System.out.println(car1.getCostModels());

//        car1.deleteModel("Model2");
//        System.out.println(car1.getNameModels());


//        Motorcycle moto = new Motorcycle("Bmw", 5);
//        System.out.println(moto.getNameModels());
//        System.out.println(moto.getCostModels());

//        moto.setNameModel("Model3", "Model3");
//        System.out.println(moto.getNameModels());

//        System.out.println(moto.getCostByName("Model3"));

//        moto.setCostByName("Model3", 1_000_000);
//        System.out.println(moto.getCostModels());

//        moto.addModel("Model2", 1_000_001);
//        System.out.println(moto.getNameModels());
//        System.out.println(moto.getCostModels());

//        moto.deleteModel("Model3");
//        System.out.println(moto.getNameModels());

        Transport tr1 = new Car("Lada", 5);
        TransportAbs.printNamesModels(tr1);
        TransportAbs.printCostsModels(tr1);
        TransportAbs.getAverage(tr1);
    }
}