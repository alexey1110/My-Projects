public class Screen {
    private final double diagonal;
    private final TypeOfScreen type;
    private final double weight;

    public Screen(double diagonal, TypeOfScreen type, double weight) {
        this.diagonal = diagonal;
        this.type = type;
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }
    public String toString(){
        return "\nДиагональ - " + diagonal + " д." +
                "\nТип - " + type +
                "\nВес - " + weight + " кг.";
    }
}
