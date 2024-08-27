public class StorageOfInformation {
    private final TypeOfStorage type;
    private final int volume;
    private final double weight;
    public StorageOfInformation(TypeOfStorage type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }
    public double getWeight(){
        return weight;
    }
    public String toString(){
        return "\nТип - " + type +
                "\nОбъём памяти - " + volume + " Гбайт" +
                "\nВес - " + weight + " кг";
    }

}
