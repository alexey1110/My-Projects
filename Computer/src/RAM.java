public class RAM {
    private final TypeOfRam type;
    private final int volume;
    private final double weight;

    public RAM(TypeOfRam type, int volume, double weight) {
        this.type = type;
        this.volume = volume;
        this.weight = weight;
    }

    public TypeOfRam getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public double getWeight() {
        return weight;
    }
    public String toString(){
        return "\nТип - " + type +
                "\nОбъём - " + volume + " Гбайт" +
                "\nВес - " + weight + " кг.";
    }
}
