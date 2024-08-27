public class Keyboard {
    private final TypeOfKeyboard type;
    private final ScreenBackLight screenBackLight;
    private final double weight;

    public double getWeight() {
        return weight;
    }

    public Keyboard(TypeOfKeyboard type, ScreenBackLight screenBackLight, double weight) {
        this.type = type;
        this.screenBackLight = screenBackLight;
        this.weight = weight;
    }
    public String toString(){
        return "\nТип - " + type +
                "\nНаличие подсветк - " + screenBackLight +
                "\nВес - " + weight + " кг.";
    }
}
