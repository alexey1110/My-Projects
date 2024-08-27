public class Processor {
    private final double frequency;
    private final int countOfCores;
    private final String manufacturer;
    private final double weight;

    public Processor(double frequency, int countOfCores,
                     String manufacturer, double weight){
        this.frequency = frequency;
        this.countOfCores = countOfCores;
        this.manufacturer =manufacturer;
        this.weight =weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCountOfCores() {
        return countOfCores;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getWeight() {
        return weight;
    }
    public String toString(){
        return "\nЧасота - " + frequency + " Гц" +
                "\nКоличество ядер - " + countOfCores +
                "\nПроизводитель - " + manufacturer +
                "\nВес - " + weight + " кг.";
    }
}
